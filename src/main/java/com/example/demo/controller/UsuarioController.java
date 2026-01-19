package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {


    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    @GetMapping
    public List<Usuario> listar() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existente.setNombre(usuario.getNombre());
        existente.setEmail(usuario.getEmail());

        return repository.save(existente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
