package com.moriartynho.msinscricaoeducacaoinfantil.service.validation.student_register;

import org.springframework.stereotype.Component;

import com.moriartynho.msinscricaoeducacaoinfantil.dto.StudentRegisterDTO;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.RegisterValidationException;

@Component
public class ValidateStudentAndGuardianNameAreTheSame implements StudentRegisterValidation {

	@Override
	public void validate(StudentRegisterDTO studentRegisterDTO) throws RegisterValidationException {
		if (studentRegisterDTO.studentsFullName().equals(studentRegisterDTO.studentsGuardianName())) {
			throw new RegisterValidationException("nome do estudante e nome do responsável não pode ser o mesmo");
		}
	}

}
