package com.moriartynho.msinscricaoeducacaoinfantil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.moriartynho.msinscricaoeducacaoinfantil.dto.RegisterSchoolDTO;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.InternalErrorException;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.RegisterValidationException;
import com.moriartynho.msinscricaoeducacaoinfantil.model.School;
import com.moriartynho.msinscricaoeducacaoinfantil.repository.SchoolRepository;

@Service
public class SchoolService {

	@Autowired
	private SchoolRepository schoolRepository;

	public List<School> findAllSchools() throws InternalErrorException {
		try {
			return schoolRepository.findAll();
		} catch (DataAccessException e) {
			throw new InternalErrorException("Erro ao acessar a base de dados: " + e.getMessage(), e);
		}
	}

	public void registerSchool(RegisterSchoolDTO registerSchoolDTO)
			throws RegisterValidationException, InternalErrorException {
		if (validateSchoolNameAlreadyExists(registerSchoolDTO)) {
			throw new RegisterValidationException("Escola com mesmo nome já registrada");
		}

		try {
			School newSchool = new School(null, registerSchoolDTO.schoolName(), registerSchoolDTO.schoolAndress(),
					registerSchoolDTO.physicalRooms(), registerSchoolDTO.schoolClasses());
			schoolRepository.save(newSchool);
		} catch (DataAccessException e) {
			throw new InternalErrorException("Erro ao acessar a base de dados: " + e.getMessage(), e);
		}
	}

	private boolean validateSchoolNameAlreadyExists(RegisterSchoolDTO registerSchoolDTO) throws InternalErrorException {
		try {
			return schoolRepository.existsBySchoolName(registerSchoolDTO.schoolName());
		} catch (DataAccessException e) {
			throw new InternalErrorException("Erro ao acessar a base de dados: " + e.getMessage(), e);
		}
	}

	public void insertSchoolClassBySchoolName(String schoolName, Integer numberOfVacancies) {
		// TODO Auto-generated method stub
		
	}
}
