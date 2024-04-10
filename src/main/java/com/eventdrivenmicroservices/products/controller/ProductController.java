package com.eventdrivenmicroservices.products.controller;

import com.eventdrivenmicroservices.products.model.Product;
import com.eventdrivenmicroservices.products.service.implementation.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Add controller methods here
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        String productId = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }
}
