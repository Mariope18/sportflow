package com.sportflow.auth.controller;

import com.sportflow.auth.model.User;
import com.sportflow.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {

        authService.register(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("Registrazione effettuata con successo");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User requestUser) {
        return ResponseEntity.ok(authService.login(requestUser));
    }
}
