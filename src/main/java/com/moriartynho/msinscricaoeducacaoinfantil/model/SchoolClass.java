package com.moriartynho.msinscricaoeducacaoinfantil.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
@AllArgsConstructor
@EqualsAndHashCode
public class SchoolClass {

	@Id
	@Indexed(unique = true)
	private String id;

	@NotNull
	private Integer maximumVacanciesInTheClass;

	private List<Grade> classGrade;

}
