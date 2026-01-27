package com.example.demo.usuario;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        usuarioRepository.deleteAll();
    }

    // ✅ TEST CREAR USUARIO
    @Test
    void deberiaCrearUsuario() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setNombre("Miguel");
        usuario.setEmail("miguel@test.com");
        usuario.setPassword("123456");

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.email").value("miguel@test.com"));
    }

    // ✅ TEST LISTAR USUARIOS
    @Test
    void deberiaListarUsuarios() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setNombre("Miguel");
        usuario.setEmail("miguel@test.com");
        usuario.setPassword("123456");

        usuarioRepository.save(usuario);

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("miguel@test.com"));
    }

    // ✅ TEST ELIMINAR USUARIO
    @Test
    void deberiaEliminarUsuario() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setNombre("Miguel");
        usuario.setEmail("miguel@test.com");
        usuario.setPassword("123456");

        Usuario guardado = usuarioRepository.save(usuario);

        mockMvc.perform(delete("/api/usuarios/" + guardado.getId()))
                .andExpect(status().isOk());
    }
}
