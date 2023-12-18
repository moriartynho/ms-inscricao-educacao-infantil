package com.moriartynho.msinscricaoeducacaoinfantil.service.validation.insert_class;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moriartynho.msinscricaoeducacaoinfantil.exception.RegisterValidationException;
import com.moriartynho.msinscricaoeducacaoinfantil.model.Grade;
import com.moriartynho.msinscricaoeducacaoinfantil.model.School;

@Service
public interface InsertClassValidation {

	void validate(School schoolToAddClass, List<Grade> gradeOfTheNewClass) throws RegisterValidationException;
}
