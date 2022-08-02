package com.example.task1.service;

import com.example.task1.enity.User;
import com.example.task1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;


    public void register(String email, String password) {
        userRepo.existsByEmail(email);

        var user = User.builder()
                .email(email)
                .password(encoder.encode(password))
                .build();

        userRepo.save(user);
    }
}
