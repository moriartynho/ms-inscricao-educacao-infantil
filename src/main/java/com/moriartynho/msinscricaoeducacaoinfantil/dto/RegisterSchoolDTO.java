package com.moriartynho.msinscricaoeducacaoinfantil.dto;

import java.util.List;

import com.moriartynho.msinscricaoeducacaoinfantil.model.SchoolClass;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterSchoolDTO(
		@NotBlank(message = "nome da escola não pode ser vazio")
		String schoolName,

		@NotBlank(message = "endereço da escola não pode ser vazio")
		String schoolAndress,
		
		@NotNull
		Integer physicalRooms,
		
		List<SchoolClass> schoolClasses 
		) {

}
