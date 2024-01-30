package com.example.sistemapagamento.dto;

import com.example.sistemapagamento.entity.User;

public record UserRequest(String name, String email, String password, String role) {

    public User toModel() {
        return new User(name, email, password, role);
    }
}
