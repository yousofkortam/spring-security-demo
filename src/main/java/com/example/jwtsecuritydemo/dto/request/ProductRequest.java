package com.example.jwtsecuritydemo.dto.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private float price;
    private int categoryId;
}
