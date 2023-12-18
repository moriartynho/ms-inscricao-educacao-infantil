package com.moriartynho.msinscricaoeducacaoinfantil.service.validation.student_register;

import org.springframework.stereotype.Component;

import com.moriartynho.msinscricaoeducacaoinfantil.dto.StudentRegisterDTO;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.RegisterValidationException;

@Component
public interface StudentRegisterValidation {

	void validate(StudentRegisterDTO studentRegisterDTO) throws RegisterValidationException;

}
