package com.moriartynho.msinscricaoeducacaoinfantil.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
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
public class Student {

	@Id
	@Indexed(unique = true)
	private String id;

	@NotBlank
	private String studentsFullName;

	@NotBlank
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate studentsBirthDate;

	@CPF
	@Indexed(unique = true)
	private String studentsCpf;

	@NotBlank
	private String studentsGender;

	@NotBlank
	private String studentsGuardianName;

	@CPF
	@NotBlank
	private String studentsGuardianCPF;

	@NotBlank
	private String studentsAddress;

	@NotNull
	private Boolean participatesAuxilioBrasil;

	private Grade studentGrade;

}
