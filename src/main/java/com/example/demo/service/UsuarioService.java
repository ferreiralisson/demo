package com.example.demo.service;

import com.example.demo.dto.UsuarioRequestDTO;
import com.example.demo.dto.UsuarioResponseDTO;
import com.example.demo.model.Endereco;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EnderecoService enderecoService;

    public void criarUsuario(UsuarioRequestDTO usuarioRequestDTO) {

        Usuario usuario = new Usuario(
                usuarioRequestDTO.getEmail(),
                usuarioRequestDTO.getNome(),
                usuarioRequestDTO.getSenha()
                );

        if(StringUtils.hasText(usuarioRequestDTO.getCep())){
            var endereco = enderecoService.buscarEnderecoPeloCep(usuarioRequestDTO.getCep());
            if(endereco != null){
                endereco.setUsuario(usuario);
                usuario.setEnderecos(List.of(endereco));
            }
        }

        repository.save(usuario);
    }

    public List<UsuarioResponseDTO> listarUsuarios() {
        return repository.findAll()
                .stream()
                .map(this::getUsuarioResponse)
                .collect(Collectors.toList());
    }

    private UsuarioResponseDTO getUsuarioResponse(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getEnderecos()
        );
    }

    public void atualizarUsuario(UsuarioRequestDTO usuarioRequestDTO, Long id) {
        // select * from usuario u where u.id = :id
        Usuario usuario = buscarUsuario(id);

        usuario.setNome(usuarioRequestDTO.getNome());
       usuario.setEmail(usuarioRequestDTO.getEmail());
       usuario.setSenha(usuario.getSenha());

       repository.save(usuario);
    }

    public void atualizarEmail(String email, Long id) {
        Usuario usuario = buscarUsuario(id);
        usuario.setEmail(email);
        repository.save(usuario);
    }

    private Usuario buscarUsuario(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
    }

    public void removerUsuario(Long id) {
        repository.delete(buscarUsuario(id));
    }
}
