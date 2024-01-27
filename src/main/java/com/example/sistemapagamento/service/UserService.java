package com.example.sistemapagamento.service;

import com.example.sistemapagamento.dto.UserResponse;
import com.example.sistemapagamento.entity.User;
import com.example.sistemapagamento.repository.UserRepository;
import com.example.sistemapagamento.util.RandomString;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final MailService emailSender;

    public UserResponse registerUser(User user) throws MessagingException, UnsupportedEncodingException {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists!");
        } else {
            String passwordEncoded = passwordEncoder.encode(user.getPassword());
            user.setPassword(passwordEncoded);

            String randomCode = RandomString.generateRandomString(32);
            user.setVerificationCode(randomCode);
            user.setEnable(false);

            User savedUser = userRepository.save(user);

            UserResponse response = new UserResponse(
                    savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getPassword()
            );

            emailSender.sendVerificationEmail(user);
            return response;
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
