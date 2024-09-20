package com.scaler.productservice.service;

import com.scaler.productservice.DTOs.FakeStoreProductsDTO;
import com.scaler.productservice.exception.NotValidCategoryException;
import com.scaler.productservice.exception.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long ProductId) throws ProductNotFoundException {
        ResponseEntity<FakeStoreProductsDTO> fakeStoreProductsDTOResponse = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/" + ProductId,
                FakeStoreProductsDTO.class
        );
        FakeStoreProductsDTO fakeStoreProduct = fakeStoreProductsDTOResponse.getBody();

        if (fakeStoreProduct != null) {
            throw new ProductNotFoundException(" Invalid Product ID");
        }
        return fakeStoreProduct.toProduct();
    }

    @Override
    public Product createProduct(String title, Double price, String Category, String description, String image) {
        FakeStoreProductsDTO request = new FakeStoreProductsDTO();
        request.setTitle(title);
        request.setPrice(price);
        request.setCategory(Category);
        request.setDescription(description);
        request.setImage(image);
        FakeStoreProductsDTO response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                request, // request body
                FakeStoreProductsDTO.class
        );
        if (response == null) {
            return new Product();
        }
        return response.toProduct();
    }


    @Override
    public Product updateProduct(Long productId, String title, Double price, String category, String description, String image) {
        FakeStoreProductsDTO request = new FakeStoreProductsDTO();
        request.setId(productId);
        request.setTitle(title);
        request.setPrice(price);
        request.setCategory(category);
        request.setDescription(description);
        request.setImage(image);

        restTemplate.put("https://fakestoreapi.com/products/" + productId, request);

        return request.toProduct();
    }

    @Override
    public Long deleteProduct(Long productId) throws ProductNotFoundException {
        Product deletedProduct = getSingleProduct(productId);
        if (deletedProduct == null) {
            throw new ProductNotFoundException("Product with id " + productId + " is not a valid productId");
        }
        restTemplate.delete("https://fakestoreapi.com/products/" + productId);

        return productId;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductsDTO[] response = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductsDTO[].class);

        if (response == null) {
            return null;
        }
        List<Product> allProducts = new ArrayList<>();

        for (FakeStoreProductsDTO fakeStoreProducts : response) {
            Product product = fakeStoreProducts.toProduct();

            Category temp = new Category();
            temp.setTitle(fakeStoreProducts.getTitle());
            product.setCategory(temp);
            allProducts.add(product);

        }
        return allProducts;
    }

    @Override
    public List<Category> getAllCategories() {
        String[] allCategoryList = restTemplate.getForObject("https://fakestoreapi.com/products/categories",
                String[].class);
        if (allCategoryList == null) {
            return null;
        }
        List<Category> allCategory = new ArrayList<>();
        for (String category : allCategoryList) {
            Category tempCategory = new Category();
            tempCategory.setTitle(category);
            allCategory.add(tempCategory);
        }

        return allCategory;
    }

    @Override
    public List<Product> getAllProductsCategoryWise(String category) throws NotValidCategoryException{
        FakeStoreProductsDTO[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + category,
                FakeStoreProductsDTO[].class
        );
        if (response == null) {
            throw new NotValidCategoryException("Category name : " + category + " is not a valid category, please provide valid category name");
        }
        List<Product> allProducts = new ArrayList<>();
        for (FakeStoreProductsDTO fakeStoreProducts : response) {
            allProducts.add(fakeStoreProducts.toProduct());
        }
        return allProducts;
    }
}
