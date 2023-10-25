package com.example.jwtsecuritydemo.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    String username;
    String password;
}
