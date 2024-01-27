package com.example.sistemapagamento.controller;

import com.example.sistemapagamento.dto.UserRequest;
import com.example.sistemapagamento.dto.UserResponse;
import com.example.sistemapagamento.entity.User;
import com.example.sistemapagamento.repository.UserRepository;
import com.example.sistemapagamento.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest request) {

        User user = request.toModel();
        UserResponse savedUser = userService.registerUser(user);
        return ResponseEntity.ok().body(savedUser);
    }

    @GetMapping
    public List<User> listAll() {
        return userRepository.findAll();
    }

}
