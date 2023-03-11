package com.example.productservice.dto;

import java.math.BigDecimal;

public class ProductRequest {
 
  private String name;
  private String description;
  private BigDecimal price;
public void setName(String name) {
    this.name = name;
}
public void setDescription(String description) {
    this.description = description;
}
public void setPrice(BigDecimal price) {
    this.price = price;
}
public String getName() {
    return name;
}
public String getDescription() {
    return description;
}
public BigDecimal getPrice() {
    return price;
}
public ProductRequest() {
}



}
