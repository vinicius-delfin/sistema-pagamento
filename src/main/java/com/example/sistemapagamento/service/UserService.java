package com.example.sistemapagamento.service;

import com.example.sistemapagamento.dto.UserResponse;
import com.example.sistemapagamento.entity.User;
import com.example.sistemapagamento.repository.UserRepository;
import com.example.sistemapagamento.util.RandomString;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserResponse registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists!");
        } else {
            String passwordEncoded = passwordEncoder.encode(user.getPassword());
            user.setPassword(passwordEncoded);

            String randomCode = RandomString.generateRandomString(32);
            user.setVerificationCode(randomCode);
            user.setEnable(false);

            User savedUser = userRepository.save(user);

            return new UserResponse(
                    savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getPassword()
            );
        }
    }

    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnable(true);
            userRepository.save(user);

            return true;
        }
    }
}
