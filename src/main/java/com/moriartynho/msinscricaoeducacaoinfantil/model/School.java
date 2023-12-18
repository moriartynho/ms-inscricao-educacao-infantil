package com.moriartynho.msinscricaoeducacaoinfantil.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

	@NotNull
	private Integer physicalRoomsAvailable;

	private List<SchoolClass> schoolClasses = new ArrayList<>();
}
