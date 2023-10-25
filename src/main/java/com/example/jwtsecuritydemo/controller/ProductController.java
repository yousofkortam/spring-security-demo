package com.example.jwtsecuritydemo.controller;

import com.example.jwtsecuritydemo.entity.Product;
import com.example.jwtsecuritydemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @DeleteMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );
        productRepository.delete(product);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
