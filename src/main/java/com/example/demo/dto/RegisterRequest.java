package com.example.demo.dto;

/*Registro*/
public record RegisterRequest(
        String nombre,
        String email,
        String password
) {}
