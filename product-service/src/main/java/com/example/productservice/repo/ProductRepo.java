package com.example.productservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.productservice.model.Product;

public interface ProductRepo extends MongoRepository<Product, String> {
    
}
