package com.example.jwtsecuritydemo.service;

import com.example.jwtsecuritydemo.dto.request.LoginRequest;
import com.example.jwtsecuritydemo.dto.request.RegisterRequest;
import com.example.jwtsecuritydemo.entity.Role;
import com.example.jwtsecuritydemo.entity.User;
import com.example.jwtsecuritydemo.repository.UserRepository;
import com.example.jwtsecuritydemo.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest register) {
        User user = User.builder()
                .name(register.getName())
                .age(register.getAge())
                .username(register.getUsername())
                .password(passwordEncoder.encode(register.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return jwtService.generateToken(user.getUsername());
    }

    public String authenticate(LoginRequest login) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUsername(),
                        login.getPassword()
                )
        );
        final User user = userRepository.findByUsername(login.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("invalid username or password")
        );
        return jwtService.generateToken(user.getUsername());
    }

}
