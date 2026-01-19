package com.example.demo.dto;

public record RegisterRequest(
        String nombre,
        String email,
        String password
) {}
