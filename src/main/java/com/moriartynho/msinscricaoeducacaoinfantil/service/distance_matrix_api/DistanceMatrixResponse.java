package com.moriartynho.msinscricaoeducacaoinfantil.service.distance_matrix_api;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistanceMatrixResponse {
	
	@JsonProperty("destination_addresses")
    private String[] destinationAddresses;

    @JsonProperty("origin_addresses")
    private String[] originAddresses;

    @JsonProperty("rows")
    private Row[] rows;

    @JsonProperty("status")
    private String status;

}
