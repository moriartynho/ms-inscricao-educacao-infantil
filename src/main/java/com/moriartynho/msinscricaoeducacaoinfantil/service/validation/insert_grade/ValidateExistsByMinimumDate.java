package com.moriartynho.msinscricaoeducacaoinfantil.service.validation.insert_grade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moriartynho.msinscricaoeducacaoinfantil.dto.InsertGradeDTO;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.RegisterValidationException;
import com.moriartynho.msinscricaoeducacaoinfantil.repository.GradeRepository;

@Component
public class ValidateExistsByMinimumDate implements InsertGradeValidation {

	@Autowired
	private GradeRepository gradeRepository;

	@Override
	public void validate(InsertGradeDTO insertGradeDTO) throws RegisterValidationException {
		if (this.gradeRepository.existsByGradeMinimumDate(insertGradeDTO.gradeMinimumDate())) {
			throw new RegisterValidationException("Série com essa data mínima já existe");
		}
		
	}

}
