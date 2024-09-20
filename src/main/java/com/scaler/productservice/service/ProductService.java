package com.scaler.productservice.service;

import com.scaler.productservice.exception.NotValidCategoryException;
import com.scaler.productservice.exception.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    Product createProduct(String title, Double price, String category, String description, String image);

    Product updateProduct(Long productId, String title, Double price, String category, String description, String image) throws ProductNotFoundException;

    Long deleteProduct(Long productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    List<Category> getAllCategories();

    List<Product> getAllProductsCategoryWise(String category) throws NotValidCategoryException;
}