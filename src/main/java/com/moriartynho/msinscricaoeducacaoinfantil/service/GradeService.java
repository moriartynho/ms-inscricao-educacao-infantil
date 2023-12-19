package com.moriartynho.msinscricaoeducacaoinfantil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.moriartynho.msinscricaoeducacaoinfantil.dto.EditGradeDTO;
import com.moriartynho.msinscricaoeducacaoinfantil.dto.InsertGradeDTO;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.InternalErrorException;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.RegisterValidationException;
import com.moriartynho.msinscricaoeducacaoinfantil.model.Grade;
import com.moriartynho.msinscricaoeducacaoinfantil.repository.GradeRepository;
import com.moriartynho.msinscricaoeducacaoinfantil.service.validation.insert_grade.InsertGradeValidation;

import jakarta.validation.ValidationException;

@Service
public class GradeService {

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private List<InsertGradeValidation> insertGradeValidations;

	public List<Grade> findAllGrades() throws InternalErrorException {
		try {
			return this.gradeRepository.findAll();
		} catch (Exception e) {
			throw new InternalErrorException("Ocorreu um erro ao tentar acessar a base de dados: " + e.getMessage());
		}
	}

	public Grade editGradeById(EditGradeDTO editGradeDTO) throws InternalErrorException {
		try {
			Optional<Grade> gradeToEdit = gradeRepository.findById(editGradeDTO.id());
			return gradeRepository.save(gradeToEdit.get());
		} catch (DataAccessException e) {
			throw new InternalErrorException("Erro ao acessar a base de dados: " + e.getMessage(), e);
		}
	}

	public void insertGrade(InsertGradeDTO insertGradeDTO) throws RegisterValidationException, InternalErrorException {
		try {
			validateDateGrade(insertGradeDTO);
			Grade newGrade = new Grade(null, insertGradeDTO.gradeName(), insertGradeDTO.gradeMinimumDate(),
					insertGradeDTO.gradeMaximumDate());
			this.gradeRepository.save(newGrade);
		} catch (RegisterValidationException e) {
			throw new InternalErrorException("Erro ao acessar a base de dados: " + e.getMessage(), e);
		}
	}

	private void validateDateGrade(InsertGradeDTO insertGradeDTO) throws RegisterValidationException {
		this.insertGradeValidations.forEach(v -> {
			try {
				v.validate(insertGradeDTO);
			} catch (RegisterValidationException e) {
				throw new ValidationException("Ocorreu um erro ao validar série: " + e.getMessage());
			}
		});

	}

}
