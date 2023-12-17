package com.moriartynho.msinscricaoeducacaoinfantil.service.validation.student_register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moriartynho.msinscricaoeducacaoinfantil.dto.StudentRegisterDTO;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.RegisterValidationException;
import com.moriartynho.msinscricaoeducacaoinfantil.repository.StudentRepository;

@Service
public class ValidateStudentAlreadyRegistred implements StudentRegisterValidation {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void validate(StudentRegisterDTO studentRegisterDTO) throws RegisterValidationException {
			if (studentRepository.existsByStudentsCpf(studentRegisterDTO.studentsCpf())) {
				throw new RegisterValidationException("CPF já cadastrado na base de dados");
			}

	}

}
