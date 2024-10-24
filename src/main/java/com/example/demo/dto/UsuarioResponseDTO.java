package com.example.demo.dto;

import com.example.demo.model.Endereco;

import java.util.List;

public record UsuarioResponseDTO(
        Long id, String nome,
        String email,
        List<Endereco> enderecos) {
}
