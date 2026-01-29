package com.example.demo.dto;

/*Este es el login*/
public record LoginRequest(
        String email,
        String password
) {}