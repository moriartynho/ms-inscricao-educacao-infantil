package com.moriartynho.msinscricaoeducacaoinfantil.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.moriartynho.msinscricaoeducacaoinfantil.dto.InsertSchoolClassDTO;
import com.moriartynho.msinscricaoeducacaoinfantil.dto.RegisterSchoolDTO;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.InternalErrorException;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.RegisterValidationException;
import com.moriartynho.msinscricaoeducacaoinfantil.model.Grade;
import com.moriartynho.msinscricaoeducacaoinfantil.model.School;
import com.moriartynho.msinscricaoeducacaoinfantil.model.SchoolClass;
import com.moriartynho.msinscricaoeducacaoinfantil.repository.GradeRepository;
import com.moriartynho.msinscricaoeducacaoinfantil.repository.SchoolClassRepository;
import com.moriartynho.msinscricaoeducacaoinfantil.repository.SchoolRepository;
import com.moriartynho.msinscricaoeducacaoinfantil.service.validation.insert_class.InsertClassValidation;

import jakarta.validation.ValidationException;

@Service
public class SchoolService {

	private final SchoolRepository schoolRepository;
	private final GradeRepository gradeRepository;
	private final SchoolClassRepository schoolClassRepository;
	private final List<InsertClassValidation> insertClassValidations;

	public SchoolService(SchoolRepository schoolRepository, GradeRepository gradeRepository,
			SchoolClassRepository schoolClassRepository, List<InsertClassValidation> insertClassValidations) {
		this.schoolRepository = schoolRepository;
		this.gradeRepository = gradeRepository;
		this.schoolClassRepository = schoolClassRepository;
		this.insertClassValidations = insertClassValidations;
	}

	public List<School> findAllSchools() throws InternalErrorException {
		return schoolRepository.findAll();
	}

	public void registerSchool(RegisterSchoolDTO registerSchoolDTO)
			throws RegisterValidationException, InternalErrorException {
		if (validateSchoolNameAlreadyExists(registerSchoolDTO.schoolName())) {
			throw new RegisterValidationException("Escola com mesmo nome já registrada");
		}

		School newSchool = new School(null, registerSchoolDTO.schoolName(), registerSchoolDTO.schoolAndress(),
				registerSchoolDTO.physicalRooms(), new ArrayList<>(), new ArrayList<>());
		schoolRepository.save(newSchool);
	}

	public void insertSchoolClassBySchoolName(InsertSchoolClassDTO insertClassDTO)
			throws RegisterValidationException, InternalErrorException {
		School schoolToAddClass = findSchoolByName(insertClassDTO.schoolName());
		List<Grade> gradeOfTheNewClass = findGradeByName(insertClassDTO.classGrade());

		validateInsertClass(schoolToAddClass, gradeOfTheNewClass);

		SchoolClass newSchoolClass = new SchoolClass(null, insertClassDTO.maximumVacanciesInTheClass(),
				gradeOfTheNewClass);

		schoolClassRepository.save(newSchoolClass);
		schoolToAddClass.getSchoolClasses().add(newSchoolClass);
		schoolRepository.save(schoolToAddClass);

	}

	private School findSchoolByName(String schoolName) throws RegisterValidationException {
		return schoolRepository.findBySchoolName(schoolName)
				.orElseThrow(() -> new RegisterValidationException("Escola não encontrada"));
	}

	private List<Grade> findGradeByName(String gradeName) throws RegisterValidationException {
		return gradeRepository.findAllByGradeName(gradeName)
				.orElseThrow(() -> new RegisterValidationException("Série não encontrada"));
	}

	private void validateInsertClass(School school, List<Grade> gradeList) throws ValidationException {
		insertClassValidations.forEach(v -> {
			try {
				v.validate(school, gradeList);
			} catch (RegisterValidationException e) {
				throw new ValidationException("Ocorreu um erro ao validar turma: " + e.getMessage());
			}
		});
	}

	private boolean validateSchoolNameAlreadyExists(String schoolName) throws InternalErrorException {
		return schoolRepository.existsBySchoolName(schoolName);
	}
}
