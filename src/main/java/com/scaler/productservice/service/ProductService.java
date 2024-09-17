package com.scaler.productservice.service;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long ProductId);

    Product createProduct(String title,Double price, String Category ,String description, String image);

    Product updateProduct(Long productId, String title, Double price, String category, String description, String image)

    Long deleteProduct(Long productId)

    List<Product> getAllProducts();

    List<Category> getAllCategories();

    List<Product> getAllProductsCategoryWise(String category)

}
