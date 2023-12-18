package com.moriartynho.msinscricaoeducacaoinfantil.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record InsertSchoolClassDTO(

		@NotNull(message = "nome da escola não pode ser nulo") String schoolName,

		@NotNull(message = "número de vagas da tuma não pode ser nula") @Size(min = 1, max = 40) Integer maximumVacanciesInTheClass,

		@NotNull(message = "Série não pode ser nulo") String classGrade) {

}
