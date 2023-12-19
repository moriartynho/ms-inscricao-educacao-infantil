package com.moriartynho.msinscricaoeducacaoinfantil.service.validation.insert_grade;

import org.springframework.stereotype.Service;

import com.moriartynho.msinscricaoeducacaoinfantil.dto.InsertGradeDTO;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.RegisterValidationException;

@Service
public interface InsertGradeValidation {
	
	void validate(InsertGradeDTO insertGradeDTO) throws RegisterValidationException;

}
