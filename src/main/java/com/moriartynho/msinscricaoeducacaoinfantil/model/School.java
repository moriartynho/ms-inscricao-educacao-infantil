package com.moriartynho.msinscricaoeducacaoinfantil.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Document
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class School {

	@Id
	@Indexed(unique = true)
	private String id;

	@NotBlank
	@Indexed(unique = true)
	private String schoolName;

	@NotBlank
	private String schoolAddress;

	@NotNull
	private Integer physicalRoomsAvailable;

	private List<SchoolClass> schoolClasses = new ArrayList<>();

	private List<Student> schoolStudentRanking;
}
