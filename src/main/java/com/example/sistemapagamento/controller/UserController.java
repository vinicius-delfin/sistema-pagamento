package com.example.sistemapagamento.controller;

import com.example.sistemapagamento.dto.UserRequest;
import com.example.sistemapagamento.dto.UserResponse;
import com.example.sistemapagamento.entity.User;
import com.example.sistemapagamento.repository.UserRepository;
import com.example.sistemapagamento.service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest request) throws MessagingException, UnsupportedEncodingException {

        User user = request.toModel();
        UserResponse savedUser = userService.registerUser(user);
        return ResponseEntity.ok().body(savedUser);
    }

    @GetMapping("verify")
    public String verifyUser(@Param("code") String code) {
        if (userService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

    @GetMapping("/test")
    public String test() {
        return "Logged in!";
    }
}
