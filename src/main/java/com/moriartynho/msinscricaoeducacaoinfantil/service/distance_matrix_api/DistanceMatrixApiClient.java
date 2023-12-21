package com.moriartynho.msinscricaoeducacaoinfantil.service.distance_matrix_api;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moriartynho.msinscricaoeducacaoinfantil.exception.InternalErrorException;

@Service
public class DistanceMatrixApiClient {

	@Value("${GEO_API_KEY}")
	private String geoApiKey;

	public Integer compareAddress(String schoolAndress, String studentAndress) throws InternalErrorException, IOException, InterruptedException {
		 String encodedSchoolAndress = URLEncoder.encode(schoolAndress, "UTF-8");
	        String encodedStudentAndress = URLEncoder.encode(studentAndress, "UTF-8");
		String apiUrl = "https://maps.googleapis.com/maps/api/distancematrix/json?" + "destinations=" + encodedSchoolAndress
				+ "&language=pt-BR" + "&mode=walking" + "&origins=" + encodedStudentAndress + "&key=" + geoApiKey;

		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).build();

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		if (response.statusCode() != 200) {
			throw new InternalErrorException("Erro ao chamar a API. Código de resposta: " + response.statusCode());
		}

		String responseBody = response.body();

		ObjectMapper objectMapper = new ObjectMapper();
		DistanceMatrixResponse distanceMatrixResponse = objectMapper.readValue(responseBody,
				DistanceMatrixResponse.class);

		return distanceMatrixResponse.getRows()[0].getElements()[0].getDistance().getValue();
	}
}
