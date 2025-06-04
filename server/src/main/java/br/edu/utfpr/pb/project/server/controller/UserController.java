package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.dto.UserDto;
import br.edu.utfpr.pb.project.server.model.User;
import br.edu.utfpr.pb.project.server.repository.UserRepository;
import br.edu.utfpr.pb.project.server.service.UserService;
import br.edu.utfpr.pb.project.server.shared.GenericResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
            this.userService = userService;
            this.userRepository = userRepository;
    }

    @PostMapping
    public GenericResponse createUser(@Valid @RequestBody User user) {
        userService.save(user);
        return GenericResponse.builder().message("User saved.").build();
    }

    @GetMapping("/find")
    public ResponseEntity<?> findUserByEmail(@RequestParam String email) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            UserDto userDto = convertToDto(user);
            return ResponseEntity.ok(userDto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setCountry(user.getCountry());
        userDto.setAddress(user.getAddress());
        userDto.setLanguage(user.getLanguage());

        return userDto;
    }
}
