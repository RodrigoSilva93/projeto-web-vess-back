package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.dto.UsuarioDto;
import br.edu.utfpr.pb.project.server.model.Usuario;
import br.edu.utfpr.pb.project.server.repository.UsuarioRepository;
import br.edu.utfpr.pb.project.server.service.UsuarioService;
import br.edu.utfpr.pb.project.server.shared.GenericResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public UserController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
            this.usuarioService = usuarioService;
            this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public GenericResponse createUser(@Valid @RequestBody Usuario usuario) {
        usuarioService.save(usuario);
        return GenericResponse.builder().message("Usuário salvo.").build();
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(usuarioRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario != null) {
            UsuarioDto usuarioDto = convertToDto(usuario);
            return ResponseEntity.ok(usuarioDto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
    }

    private UsuarioDto convertToDto(Usuario usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setPais(usuario.getPais());
        usuarioDto.setEndereco(usuario.getEndereco());
        usuarioDto.setIdioma(usuario.getIdioma());

        return usuarioDto;
    }
}
