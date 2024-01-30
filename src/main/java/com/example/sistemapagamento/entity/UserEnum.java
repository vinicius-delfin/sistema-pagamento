package com.example.sistemapagamento.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserEnum {

    ADMIN("admin"),
    USER("user");

    private final String role;
}
