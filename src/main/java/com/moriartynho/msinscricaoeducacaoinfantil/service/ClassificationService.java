package com.moriartynho.msinscricaoeducacaoinfantil.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moriartynho.msinscricaoeducacaoinfantil.exception.InternalErrorException;
import com.moriartynho.msinscricaoeducacaoinfantil.model.School;
import com.moriartynho.msinscricaoeducacaoinfantil.model.SchoolClass;
import com.moriartynho.msinscricaoeducacaoinfantil.model.Student;
import com.moriartynho.msinscricaoeducacaoinfantil.model.StudentDistanceToSchool;
import com.moriartynho.msinscricaoeducacaoinfantil.repository.SchoolRepository;
import com.moriartynho.msinscricaoeducacaoinfantil.repository.StudentRepository;
import com.moriartynho.msinscricaoeducacaoinfantil.service.distance_matrix_api.DistanceMatrixApiClient;

import jakarta.validation.ValidationException;

@Service
public class ClassificationService {

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private DistanceMatrixApiClient matrixApiClient;

	public void generateRanking() {
		
		List<Student> studentsToClassify = studentRepository.findAll();
		List<School> schools = schoolRepository.findAll();
		List<StudentDistanceToSchool> studentDistances = validateDistanceAndVacancies(studentsToClassify, schools);

		studentDistances.forEach(studentDistance -> {
			School school = studentDistance.getSchool();
			Student student = studentDistance.getStudent();

			school.getSchoolStudentRanking().add(student);
			schoolRepository.save(school);
			studentRepository.save(student);
		});
	}

	private List<StudentDistanceToSchool> validateDistanceAndVacancies(List<Student> students, List<School> schools) {
		List<StudentDistanceToSchool> studentDistances = new ArrayList<>();

		students.forEach(student -> schools.forEach(school -> {
				try {
					validateAndAddDistance(student, school, studentDistances);
				} catch (Exception e) {
					throw new ValidationException(e.getMessage());
				}
		}));

		studentDistances.sort(Comparator.comparingInt(StudentDistanceToSchool::getDistance)
				.thenComparing(studentDistance -> studentDistance.getStudent().getParticipatesAuxilioBrasil()));

		return studentDistances;
	}

	private void validateAndAddDistance(Student student, School school, List<StudentDistanceToSchool> studentDistances)
			throws InternalErrorException, IOException, InterruptedException {
		if (containsStudentGrade(school, student) && containsVacancies(school, student)) {
			int distanceBetweenStudentAndSchool = matrixApiClient.compareAddress(school.getSchoolAddress(),
					student.getStudentsAddress());
			StudentDistanceToSchool studentDistanceToSchool = new StudentDistanceToSchool(school, student,
					distanceBetweenStudentAndSchool);
			studentDistances.add(studentDistanceToSchool);
		}
	}

	private boolean containsStudentGrade(School school, Student student) {
		return school.getSchoolClasses().stream()
				.anyMatch(schoolClass -> schoolClass.getClassGrade().equals(student.getStudentGrade()));
	}

	private boolean containsVacancies(School school, Student student) {
		int totalVacancies = school.getSchoolClasses().stream()
				.filter(schoolClass -> schoolClass.getClassGrade().equals(student.getStudentGrade()))
				.mapToInt(SchoolClass::getMaximumVacanciesInTheClass).sum();
		return totalVacancies <= school.getSchoolStudentRanking().size();
	}
}
