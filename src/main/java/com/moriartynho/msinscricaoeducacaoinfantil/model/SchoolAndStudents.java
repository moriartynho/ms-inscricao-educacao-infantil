package com.moriartynho.msinscricaoeducacaoinfantil.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class SchoolAndStudents {
	
	private School school;
	private Student student;

}
