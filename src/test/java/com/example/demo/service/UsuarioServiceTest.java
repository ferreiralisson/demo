package com.example.demo.service;

import com.example.demo.dto.UsuarioRequestDTO;
import com.example.demo.dto.UsuarioResponseDTO;
import com.example.demo.model.Endereco;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EnderecoService enderecoService;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarUsuario() {
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();
        usuarioRequestDTO.setEmail("test@example.com");
        usuarioRequestDTO.setNome("Test User");
        usuarioRequestDTO.setSenha("password");
        usuarioRequestDTO.setCep("12345");

        Endereco endereco = new Endereco();
        when(enderecoService.buscarEnderecoPeloCep("12345")).thenReturn(endereco);

        usuarioService.criarUsuario(usuarioRequestDTO);

        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testListarUsuarios() {
        Usuario usuario = new Usuario("test@example.com", "Test User", "password");
        when(usuarioRepository.findAll(Pageable.unpaged())).thenReturn(new PageImpl<>(List.of(usuario)));

        Page<UsuarioResponseDTO> usuarios = usuarioService.listarUsuarios(Pageable.unpaged());

        assertEquals(1, usuarios.getTotalPages());
        assertEquals("Test User", usuarios.getContent().get(0).nome());
    }

    @Test
    void testAtualizarUsuario() {
        Long id = 1L;
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();
        usuarioRequestDTO.setEmail("updated@example.com");
        usuarioRequestDTO.setNome("Updated User");
        usuarioRequestDTO.setSenha("newpassword");

        Usuario usuario = new Usuario("test@example.com", "Test User", "password");
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        usuarioService.atualizarUsuario(usuarioRequestDTO, id);

        verify(usuarioRepository, times(1)).save(usuario);
        assertEquals("Updated User", usuario.getNome());
        assertEquals("updated@example.com", usuario.getEmail());
    }

    @Test
    void testAtualizarEmail() {
        Long id = 1L;
        String newEmail = "updated@example.com";

        Usuario usuario = new Usuario("test@example.com", "Test User", "password");
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        usuarioService.atualizarEmail(newEmail, id);

        verify(usuarioRepository, times(1)).save(usuario);
        assertEquals(newEmail, usuario.getEmail());
    }

}