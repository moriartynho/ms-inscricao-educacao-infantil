package com.moriartynho.msinscricaoeducacaoinfantil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moriartynho.msinscricaoeducacaoinfantil.dto.StudentRegisterDTO;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.RegisterValidationException;
import com.moriartynho.msinscricaoeducacaoinfantil.model.Student;
import com.moriartynho.msinscricaoeducacaoinfantil.repository.StudentRepository;
import com.moriartynho.msinscricaoeducacaoinfantil.service.StudentRegisterService;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StudentController {

	@Autowired
	private StudentRegisterService registerService;

	@Autowired
	private StudentRepository studentRepository;

	@PostMapping
	public ResponseEntity<Student> StudentRegister(@RequestBody StudentRegisterDTO studentRegisterDTO)
			throws RegisterValidationException {
		registerService.register(studentRegisterDTO);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}

}
