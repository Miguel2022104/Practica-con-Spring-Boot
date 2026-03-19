package com.example.demo.dto;

/**
 * Respuesta de autenticación que incluye el token
 * y el tipo de autorización utilizado.
 */
public record AuthResponse(
        String token,
        String type
) {
}