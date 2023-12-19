package com.moriartynho.msinscricaoeducacaoinfantil.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Document
@AllArgsConstructor
public class SchoolClass {

	@Id
	private String id;

	@NotNull
	private Integer maximumVacanciesInTheClass;

	private List<Grade> classGrade;

}
