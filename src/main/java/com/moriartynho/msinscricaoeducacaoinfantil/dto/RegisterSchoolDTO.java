package com.moriartynho.msinscricaoeducacaoinfantil.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterSchoolDTO(@NotBlank(message = "nome da escola não pode ser vazio") String schoolName,

		@NotBlank(message = "endereço da escola não pode ser vazio") String schoolAndress,

		@NotNull Integer physicalRooms) {

}
