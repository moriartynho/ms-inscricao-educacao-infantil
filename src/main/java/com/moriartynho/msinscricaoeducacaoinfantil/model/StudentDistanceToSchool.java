package com.moriartynho.msinscricaoeducacaoinfantil.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDistanceToSchool {
	
	private School school;
	private Student student;
	private Integer distance;
}
