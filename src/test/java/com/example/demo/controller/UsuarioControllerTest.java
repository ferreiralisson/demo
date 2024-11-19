package com.example.demo.controller;

import com.example.demo.dto.UsuarioRequestDTO;
import com.example.demo.dto.UsuarioResponseDTO;
import com.example.demo.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {


    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarUsuario() {
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();
        doNothing().when(usuarioService).criarUsuario(usuarioRequestDTO);

        ResponseEntity<Void> response = usuarioController.criarUsuario(usuarioRequestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(usuarioService, times(1)).criarUsuario(usuarioRequestDTO);
    }

    @Test
    void testAtualizarUsuario() {
        Long id = 1L;
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();
        doNothing().when(usuarioService).atualizarUsuario(usuarioRequestDTO, id);

        ResponseEntity<Void> response = usuarioController.atualizarUsuario(id, usuarioRequestDTO);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usuarioService, times(1)).atualizarUsuario(usuarioRequestDTO, id);
    }

    @Test
    void testListarUsuarios() {
        PageImpl<UsuarioResponseDTO> pageUsuario = new PageImpl<>(List.of(new UsuarioResponseDTO(1L, "nome", "email", null)));
        when(usuarioService.listarUsuarios(any())).thenReturn(pageUsuario);

        ResponseEntity<Page<UsuarioResponseDTO>> response = usuarioController.listarUsuarios(Pageable.unpaged());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pageUsuario, response.getBody());
        verify(usuarioService, times(1)).listarUsuarios(Pageable.unpaged());
    }

    @Test
    void testAtualizarEmail() {
        Long id = 1L;
        String email = "test@example.com";
        doNothing().when(usuarioService).atualizarEmail(email, id);

        ResponseEntity<Void> response = usuarioController.atualizarEmail(id, email);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usuarioService, times(1)).atualizarEmail(email, id);
    }

    @Test
    void testRemoverUsuario() {
        Long id = 1L;
        doNothing().when(usuarioService).removerUsuario(id);

        ResponseEntity<Void> response = usuarioController.removerUsuario(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usuarioService, times(1)).removerUsuario(id);
    }

}