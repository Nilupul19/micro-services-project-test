package com.example.productservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.model.Product;
import com.example.productservice.repo.ProductRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    
    public void createProduct(ProductRequest productReqest) {

     Product product = Product.builder()
                               .name(productReqest.getName())
                               .description(productReqest.getDescription())
                               .price(productReqest.getPrice())
                               .build();
       
      productRepo.save(product);
      log.info("product: {} is saved", product.getId());
      


      

    }

    public List<ProductResponse> getAllproducts() {

         List<Product> products = productRepo.findAll();

         System.out.println("theses are products"+products.stream());
         
         return products.stream()
              .map(this::mapToProductResponse)
              .collect(Collectors.toList());
              
    }           
    
    private ProductResponse mapToProductResponse(Product p) {
        return ProductResponse.builder()
                       .id(p.getId())
                       .name(p.getId())
                       .description(p.getDescription())
                       .price(p.getPrice())
                       .build();
    }          
}
