package com.moriartynho.msinscricaoeducacaoinfantil.service.validation.student_register;

import org.springframework.stereotype.Component;

import com.moriartynho.msinscricaoeducacaoinfantil.dto.StudentRegisterDTO;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.RegisterValidationException;

@Component
public class ValidateStudentAndGuardianCPFAreTheSame implements StudentRegisterValidation {

	@Override
	public void validate(StudentRegisterDTO studentRegisterDTO) throws RegisterValidationException {
		if(studentRegisterDTO.studentsCpf().equals(studentRegisterDTO.studentsGuardianCPF())){
			throw new RegisterValidationException("CPF do estudante e CPF do responsável não pode ser o mesmo");
		}		
	}

}
