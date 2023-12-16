package com.moriartynho.msinscricaoeducacaoinfantil.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Document
public class SchoolClass {
	
	@Id
	private Long id;
	
	@NotNull
	private Integer maximumNumberOfVacanciesInTheClass;
	
	private List<Student> classStudents;

}
