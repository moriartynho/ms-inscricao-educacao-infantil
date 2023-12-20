package com.moriartynho.msinscricaoeducacaoinfantil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.moriartynho.msinscricaoeducacaoinfantil.model.School;
import com.moriartynho.msinscricaoeducacaoinfantil.model.Student;
import com.moriartynho.msinscricaoeducacaoinfantil.model.StudentClassification;
import com.moriartynho.msinscricaoeducacaoinfantil.repository.SchoolRepository;
import com.moriartynho.msinscricaoeducacaoinfantil.repository.StudentRepository;

@Service
public class ClassificationService {

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private StudentRepository studentRepository;

	public void generateRanking() {
		List<Student> studentsToClassificate = this.studentRepository.findAll();
		List<School> schools = this.schoolRepository.findAll();
	}

}
