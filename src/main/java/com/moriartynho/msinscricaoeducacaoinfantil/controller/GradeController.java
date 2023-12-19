package com.moriartynho.msinscricaoeducacaoinfantil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moriartynho.msinscricaoeducacaoinfantil.dto.EditGradeDTO;
import com.moriartynho.msinscricaoeducacaoinfantil.dto.InsertGradeDTO;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.InternalErrorException;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.RegisterValidationException;
import com.moriartynho.msinscricaoeducacaoinfantil.model.Grade;
import com.moriartynho.msinscricaoeducacaoinfantil.service.GradeService;

@RestController
@RequestMapping("/grades")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GradeController {

	@Autowired
	private GradeService gradeService;

	@GetMapping
	public ResponseEntity<List<Grade>> findAllGrades() throws InternalErrorException {
		return ResponseEntity.ok(gradeService.findAllGrades());
	}
	
	@PostMapping()
	public ResponseEntity<Grade> insertGradeById(@RequestBody InsertGradeDTO insertGradeDTO) throws InternalErrorException, RegisterValidationException {
		gradeService.insertGrade(insertGradeDTO);
		return ResponseEntity.ok().build();
	}

	@PutMapping()
	public ResponseEntity<Grade> editGradeById(@RequestBody EditGradeDTO editGradeDTO) throws InternalErrorException {
		gradeService.editGradeById(editGradeDTO);
		return ResponseEntity.ok().build();
	}

}
