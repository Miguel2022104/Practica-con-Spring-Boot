package com.example.demo.controller;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


/*Esta es la clase de Controller*/
@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {


    private final UsuarioRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;

    /*Constructor*/
    public AuthController(UsuarioRepository repo, PasswordEncoder encoder, JwtUtil jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest req) {

        if (repo.findByEmail(req.email()).isPresent()) {
            throw new RuntimeException("Email ya registrado");
        }

        Usuario u = new Usuario();
        u.setNombre(req.nombre());
        u.setEmail(req.email());
        u.setPassword(encoder.encode(req.password()));

        repo.save(u);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest req) {

        Usuario user = repo.findByEmail(req.email())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!encoder.matches(req.password(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwt.generarToken(user.getEmail());
        return new AuthResponse(token);
    }
}
