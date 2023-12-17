package com.moriartynho.msinscricaoeducacaoinfantil.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Document
@AllArgsConstructor
public class School {

	@Id
	private String id;

	@NotBlank
	private String schoolName;

	@NotBlank
	private String schoolAndress;

	@NotBlank
	private List<SchoolClass> schoolClasses;
}
