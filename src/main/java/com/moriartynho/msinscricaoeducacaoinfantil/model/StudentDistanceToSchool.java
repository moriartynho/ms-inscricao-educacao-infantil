package com.moriartynho.msinscricaoeducacaoinfantil.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class StudentDistanceToSchool {
	
	private School school;
	private Student student;
	private Integer distance;
}
