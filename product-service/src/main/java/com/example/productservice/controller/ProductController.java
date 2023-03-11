package com.example.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.service.ProductService;


@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  ProductService productService;   
 
 @PostMapping
 @ResponseStatus(HttpStatus.CREATED)
 public void createProduct(@RequestBody ProductRequest productReqest){

    productService.createProduct(productReqest);
  
 }
 
 @GetMapping
 @ResponseStatus(HttpStatus.OK)
 public List<ProductResponse> getAllProducts(){
   
   return productService.getAllproducts();
    
 }


}
