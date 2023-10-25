package com.example.jwtsecuritydemo.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String username;
    private String password;
    private int age;
}
