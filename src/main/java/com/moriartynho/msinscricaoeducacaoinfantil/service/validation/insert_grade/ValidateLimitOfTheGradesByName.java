package com.moriartynho.msinscricaoeducacaoinfantil.service.validation.insert_grade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moriartynho.msinscricaoeducacaoinfantil.dto.InsertGradeDTO;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.RegisterValidationException;
import com.moriartynho.msinscricaoeducacaoinfantil.model.Grade;
import com.moriartynho.msinscricaoeducacaoinfantil.repository.GradeRepository;

@Component
public class ValidateLimitOfTheGradesByName implements InsertGradeValidation {

	@Autowired
	private GradeRepository gradeRepository;

	@Override
	public void validate(InsertGradeDTO insertGradeDTO) throws RegisterValidationException {
		List<Grade> gradeToValidate = gradeRepository.findAllByGradeName(insertGradeDTO.gradeName())
				.orElseThrow(() -> new RegisterValidationException("Série não encontrada"));
		Integer limitOfTheRecordsPerGrade = 2;
		if (gradeToValidate.size() == limitOfTheRecordsPerGrade) {
			throw new RegisterValidationException("Essa série já possui o número máximo de registros, Considere editar um registro já existente");
		}

	}

}
