package com.moriartynho.msinscricaoeducacaoinfantil.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.moriartynho.msinscricaoeducacaoinfantil.model.enums.StudentServiceStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Document
@AllArgsConstructor
public class StudentClassification {

	@NotNull(message = "número de classificação não pode ser nulo")
	private Integer positionInTheRanking;

	@NotNull(message = "estado de atendimento não pode ser nulo")

	private StudentServiceStatus studentServiceStatus;

	private Student student;

}
