package com.moriartynho.msinscricaoeducacaoinfantil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moriartynho.msinscricaoeducacaoinfantil.exception.InternalErrorException;
import com.moriartynho.msinscricaoeducacaoinfantil.model.Grade;
import com.moriartynho.msinscricaoeducacaoinfantil.repository.GradeRepository;

@Service
public class GradeService {

	@Autowired
	private GradeRepository gradeRepository;

	public List<Grade> findAllGrades() throws InternalErrorException {
		try {
			return this.gradeRepository.findAll();
		} catch (Exception e) {
			throw new InternalErrorException("Ocorreu um erro ao tentar acessar a base de dados: " + e.getMessage());
		}
	}

}
