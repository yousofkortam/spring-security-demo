package com.example.jwtsecuritydemo.controller;

import com.example.jwtsecuritydemo.dto.request.LoginRequest;
import com.example.jwtsecuritydemo.dto.request.RegisterRequest;
import com.example.jwtsecuritydemo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        String token = authService.authenticate(login);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest register) {
        String token = authService.register(register);
        return ResponseEntity.ok(token);
    }

}
