package com.scaler.productservice.controller;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    //Constructor: Dependancy injection
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 1. Create a Product
    @PostMapping("/")
    public Product createProduct(@RequestBody CreateProductRequestDTO request) {

    }

}
