package com.moriartynho.msinscricaoeducacaoinfantil.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document
public class Grade {
	
	@Id
	private Long id;
	
	@NotBlank
	private String gradeName;
	
	@NotBlank
	private LocalDateTime gradeMinimumDate;
	
	@NotBlank
	private LocalDateTime gradeMaximumDate;

}
