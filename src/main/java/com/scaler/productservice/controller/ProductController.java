package com.scaler.productservice.controller;

import com.scaler.productservice.DTOs.CreateProductRequestDTO;
import com.scaler.productservice.DTOs.UpdateProductRequestDTO;
import com.scaler.productservice.exception.NotValidCategoryException;
import com.scaler.productservice.exception.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.service.ProductService;
import jakarta.persistence.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return productService.createProduct(request.getTitle(),request.getPrice(),request.getCategory(),request.getDescription(),request.getImage());
        // return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    // 2. get a single product
    @GetMapping("/{Id}")
    public Product getSingleProductDetails(@PathVariable("Id") Long productId) throws ProductNotFoundException {
        return productService.getSingleProduct(productId);
        // return new ResponseEntity<>(product, HttpStatus.FOUND);
    }

    // 3. update a product
    @PutMapping("/{Id}")
    public Product updateProduct(@PathVariable("Id") Long productId,
                                 @RequestBody UpdateProductRequestDTO updateRequest) throws ProductNotFoundException  {

        return productService.updateProduct (productId, updateRequest.getTitle(), updateRequest.getPrice(), updateRequest.getCategory(), updateRequest.getDescription(), updateRequest.getTitle());
        // return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
    }

    // 4. delete a product
    @DeleteMapping("/{Id}")
    public Long deleteProduct(@PathVariable("Id") Long productId) throws ProductNotFoundException {
        return productService.deleteProduct(productId);
        // return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

    // 5. get all categories
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> allCategories = productService.getAllCategories();
        return new ResponseEntity<>(allCategories, HttpStatus.FOUND);
    }

    // 6. get all products
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.FOUND);
    }

    // 7. get all products category wise
    @GetMapping("category/{category}")
    public ResponseEntity<List<Product>> getAllProductsCategoryWise(@PathVariable("category") String category) throws NotValidCategoryException {
        List<Product> allProducts = productService.getAllProductsCategoryWise(category);
        return new ResponseEntity<>(allProducts, HttpStatus.FOUND);
    }

}
