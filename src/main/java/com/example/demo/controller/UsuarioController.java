package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/*Esta es la clase de controlller de Usuario*/


@RestController
@RequestMapping("/api/usuarios") /*Ruta para acceder a los usuarios mediante API*/
@CrossOrigin(origins = "*")
public class UsuarioController {


    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    /*Metodo POST para guardar usuarios*/
    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    /*Metodo GET para guardar usuarios*/
    @GetMapping
    public List<Usuario> listar() {
        return repository.findAll();
    }

    /*Metodo PUT para guardar usuarios*/
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existente.setNombre(usuario.getNombre());
        existente.setEmail(usuario.getEmail());

        return repository.save(existente);
    }

    /*Metodo DELETE para guardar usuarios*/
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
