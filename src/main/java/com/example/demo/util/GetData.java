package com.example.demo.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GetData {

    private final RestTemplate restTemplate;

    public GetData(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> sendRequest(
            String url,
            HttpMethod method,
            Object requestBody,
            Class<T> responseType,
            HttpHeaders headers
    ){
        HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(url, method, entity, responseType);
    }
}
