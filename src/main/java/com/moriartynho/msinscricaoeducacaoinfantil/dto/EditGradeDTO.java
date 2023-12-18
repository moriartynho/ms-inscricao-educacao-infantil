package com.moriartynho.msinscricaoeducacaoinfantil.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record EditGradeDTO(

		String id,

		String gradeName,

		@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING) LocalDate gradeMinimumDate,

		@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING) LocalDate gradeMaximumDate) {

}
