package com.example.sistemapagamento.dto;

import java.util.UUID;

public record UserResponse(UUID id, String name, String email, String password) {
}
