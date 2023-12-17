package com.moriartynho.msinscricaoeducacaoinfantil.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentRegisterDTO(
		@NotBlank(message = "nome não pode estar vazio") 
		String studentsFullName,
		
		@NotBlank(message = "data de nascimento não pode ser vazia")
		@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
		LocalDate studentsBirthDate,
		
		@CPF(message = "insira um CPF válido") 
		@NotBlank(message = "CPF do aluno não pode ser vazio")
		String studentsCpf,
		
		@NotBlank(message = "gênero do estudante não pode ser vazio") 
		String studentsGender,
		
		@NotBlank(message = "nome do responsável não pode ser vazio")
		String studentsGuardianName,
		
		@CPF(message = "insira um CPF válido")  
		@NotBlank(message = "CPF do responsável não pode ser vazio")
		String studentsGuardianCPF,
		
		@NotBlank(message = "endereço não pode ser vazio")
		String studentsAndress,
		
		@NotNull(message = "o campo de verificação de participação no Auxílio Brasil não pode ser nulo")
		Boolean participatesAuxilioBrasil
		) {

}
