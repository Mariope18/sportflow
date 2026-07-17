package com.sportflow.auth.service;

import com.sportflow.auth.enums.Role;
import com.sportflow.auth.model.User;
import com.sportflow.auth.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(User user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException(String.format("Utente già esististe con username: %s", user.getUsername()));
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException(String.format("Utente già esististe con email: %s", user.getEmail()));
        }

        String password = passwordEncoder.encode(user.getPassword());

        user.setPassword(password);
        user.setRole(Role.USER);

        userRepository.save(user);

    }
}
