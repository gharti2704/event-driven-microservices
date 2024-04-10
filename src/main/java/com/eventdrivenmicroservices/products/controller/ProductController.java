package com.eventdrivenmicroservices.products.controller;

import com.eventdrivenmicroservices.products.model.Product;
import com.eventdrivenmicroservices.products.service.implementation.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;
    private final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Add controller methods here
    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        String productId = null;
        try {
            productId = productService.createProduct(product);
        } catch (Exception e) {
            LOGGER.error("Error occurred while creating product: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(new Date(), e.getMessage(), "/products"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }
}
