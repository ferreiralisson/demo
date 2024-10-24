package com.example.demo.service;

import com.example.demo.model.Endereco;
import com.example.demo.util.GetData;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoService {

    public Endereco buscarEnderecoPeloCep(String cep){
        RestTemplate restTemplate = new RestTemplate();
        GetData dadosDoEndereco = new GetData(restTemplate);

        return dadosDoEndereco.sendRequest(
                "https://cep.awesomeapi.com.br/json/" + cep,
                HttpMethod.GET,
                null,
                Endereco.class,
                new HttpHeaders()
        ).getBody();
    }
}
