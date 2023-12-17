package com.moriartynho.msinscricaoeducacaoinfantil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moriartynho.msinscricaoeducacaoinfantil.model.Grade;
import com.moriartynho.msinscricaoeducacaoinfantil.service.GradeService;

@RestController
@RequestMapping("/grades")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GradeController {
	
	@Autowired
	private GradeService gradeService;
	
	@GetMapping
	public ResponseEntity<List<Grade>> findAllGrades(){
		return ResponseEntity.ok(gradeService.findAllGrades());
	}

}
