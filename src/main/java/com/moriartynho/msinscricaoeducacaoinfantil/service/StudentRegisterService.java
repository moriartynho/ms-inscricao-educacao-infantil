package com.moriartynho.msinscricaoeducacaoinfantil.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moriartynho.msinscricaoeducacaoinfantil.dto.StudentRegisterDTO;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.RegisterValidationException;
import com.moriartynho.msinscricaoeducacaoinfantil.model.Grade;
import com.moriartynho.msinscricaoeducacaoinfantil.model.Student;
import com.moriartynho.msinscricaoeducacaoinfantil.model.constants.GradeConstants;
import com.moriartynho.msinscricaoeducacaoinfantil.repository.GradeRepository;
import com.moriartynho.msinscricaoeducacaoinfantil.repository.StudentRepository;
import com.moriartynho.msinscricaoeducacaoinfantil.service.validation.student_register.StudentRegisterValidation;

import jakarta.validation.ValidationException;

@Service
public class StudentRegisterService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private List<StudentRegisterValidation> registerValidations;

	public void register(StudentRegisterDTO studentRegisterDTO) throws RegisterValidationException {

		try {
			this.registerValidations.forEach(v -> {
				try {
					v.validate(studentRegisterDTO);
				} catch (Exception e) {
					throw new ValidationException("Ocorreu um erro ao validar o estudante: " + e.getMessage());
				}
			});

			Grade studentGrade = setStudentGrade(studentRegisterDTO.studentsBirthDate());
			Student newStudent = new Student(null, studentRegisterDTO.studentsFullName(),
					studentRegisterDTO.studentsBirthDate(), studentRegisterDTO.studentsCpf(),
					studentRegisterDTO.studentsGender(), studentRegisterDTO.studentsGuardianName(),
					studentRegisterDTO.studentsGuardianCPF(), studentRegisterDTO.studentsAndress(),
					studentRegisterDTO.participatesAuxilioBrasil(), studentGrade);

			studentRepository.save(newStudent);
		} catch (Exception e) {
			throw new RegisterValidationException(e.getMessage());
		}
	}

	private Grade setStudentGrade(LocalDate studentsBirthDate) throws RegisterValidationException {
	    List<Grade> allGrades = gradeRepository.findAll();

	    for (Grade grade : allGrades) {
	        LocalDate minDate = grade.getGradeMinimumDate();
	        LocalDate maxDate = grade.getGradeMaximumDate();

	        boolean isYearInRange = studentsBirthDate.getYear() >= maxDate.getYear() && studentsBirthDate.getYear() <= minDate.getYear();
	        boolean isMonthInRange = studentsBirthDate.getMonthValue() >= minDate.getMonthValue() && studentsBirthDate.getMonthValue() <= maxDate.getMonthValue();

	        if (isYearInRange && isMonthInRange) {
	            return grade;
	        }
	    }

	    return gradeRepository.findByGradeName(GradeConstants.ENSINO_FUNDAMENTAL_GRADE_NAME)
	                          .orElseThrow(() -> new RegisterValidationException("Série não definida"));
	}

}
