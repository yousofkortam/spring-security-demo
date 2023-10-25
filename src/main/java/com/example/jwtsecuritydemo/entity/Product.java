package com.example.jwtsecuritydemo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float price;
    @ManyToOne
    private Category category;
}
