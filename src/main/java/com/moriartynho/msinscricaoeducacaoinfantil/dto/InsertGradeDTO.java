package com.moriartynho.msinscricaoeducacaoinfantil.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InsertGradeDTO(
		@NotBlank String gradeName,

		@NotBlank @NotNull @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING) LocalDate gradeMinimumDate,

		@NotBlank @NotNull @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING) LocalDate gradeMaximumDate) {

}
