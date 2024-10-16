package com.example.demo.controller;

import com.example.demo.dto.UsuarioRequestDTO;
import com.example.demo.dto.UsuarioResponseDTO;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> criarUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO){
        service.criarUsuario(usuarioRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarUsuario(@PathVariable Long id,
                                                 @RequestBody UsuarioRequestDTO usuarioRequestDTO){
        service.atualizarUsuario(usuarioRequestDTO, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios(){
        return ResponseEntity.ok(service.listarUsuarios());
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> atualizarEmail(
            @PathVariable Long id,
            @RequestParam(name = "email") String email){
        service.atualizarEmail(email, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removerUsuario(@PathVariable Long id){
        service.removerUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
