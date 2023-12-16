package com.moriartynho.msinscricaoeducacaoinfantil.model;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document
public class Student {
	
	@Id
	private Long id;
	
	@NotBlank
	private String studentsFullName;
	
	@NotBlank
	private LocalDateTime studentsBirthDate;
	
	@CPF
	private String studentsCpf;
	
	@NotBlank
	private String studentsGender;
	
	@NotBlank
	private String studentsGuardianName;
	
	@CPF
	@NotBlank
	private String studentsGuardianCPF;
	
	@NotBlank
	private String studentsAndress;
	

}
