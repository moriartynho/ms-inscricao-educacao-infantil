package com.moriartynho.msinscricaoeducacaoinfantil.service.distance_matrix_api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Element {
	
	private Distance distance;
    private Duration duration;
    private String status;

}
