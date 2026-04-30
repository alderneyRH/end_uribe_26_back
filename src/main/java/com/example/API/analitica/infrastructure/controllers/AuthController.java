package com.example.API.analitica.infrastructure.controllers;

import com.example.API.analitica.domain.models.Usuario;
import com.example.API.analitica.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        if(repository.findByUsername(usuario.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "El usuario ya existe"));
        }
        return ResponseEntity.ok(repository.save(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        Optional<Usuario> user = repository.findByUsername(body.get("username"));
        if (user.isPresent() && user.get().getPassword().equals(body.get("password"))) {
            return ResponseEntity.ok(Map.of("message", "Login exitoso", "user", user.get().getUsername()));
        }
        return ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas"));
    }
}