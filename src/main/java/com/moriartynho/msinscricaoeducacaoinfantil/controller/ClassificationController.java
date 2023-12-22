package com.moriartynho.msinscricaoeducacaoinfantil.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moriartynho.msinscricaoeducacaoinfantil.exception.InternalErrorException;
import com.moriartynho.msinscricaoeducacaoinfantil.model.School;
import com.moriartynho.msinscricaoeducacaoinfantil.service.ClassificationService;

@RestController
@RequestMapping("/classification")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClassificationController {

	@Autowired
	private ClassificationService classificationService;

	@PostMapping
	public ResponseEntity<List<School>> generateRanking() throws InternalErrorException, IOException, InterruptedException {
		classificationService.generateRanking();
		return ResponseEntity.ok().build();
	}

}
