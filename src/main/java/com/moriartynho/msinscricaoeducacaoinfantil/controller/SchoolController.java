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

import com.moriartynho.msinscricaoeducacaoinfantil.dto.InsertSchoolClassDTO;
import com.moriartynho.msinscricaoeducacaoinfantil.dto.RegisterSchoolDTO;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.InternalErrorException;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.RegisterValidationException;
import com.moriartynho.msinscricaoeducacaoinfantil.model.School;
import com.moriartynho.msinscricaoeducacaoinfantil.service.SchoolService;

@RestController
@RequestMapping("/schools")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SchoolController {

	@Autowired
	private SchoolService schoolService;

	@GetMapping
	public ResponseEntity<List<School>> findAllSchools() throws InternalErrorException {
		return ResponseEntity.ok(schoolService.findAllSchools());
	}

	@PostMapping
	public ResponseEntity<School> registerSchool(@RequestBody RegisterSchoolDTO registerSchoolDTO)
			throws RegisterValidationException, InternalErrorException {
		schoolService.registerSchool(registerSchoolDTO);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/create-class")
	public ResponseEntity<School> insertSchoolClassBySchoolName(@RequestBody InsertSchoolClassDTO insertClassDTO)
			throws RegisterValidationException, InternalErrorException {
		schoolService.insertSchoolClassBySchoolName(insertClassDTO);
		return ResponseEntity.ok().build();
	}

}
