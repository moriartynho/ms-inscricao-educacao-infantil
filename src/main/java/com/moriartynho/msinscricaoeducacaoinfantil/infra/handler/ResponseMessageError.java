package com.moriartynho.msinscricaoeducacaoinfantil.infra.handler;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessageError {

	private Instant timestamp;
	private Integer status;
	private String message;
	private String path;
}
