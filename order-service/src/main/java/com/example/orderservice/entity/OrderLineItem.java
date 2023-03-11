package com.example.orderservice.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orderlineitem_tbl")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItem {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;
 private String skuCode;
 private BigDecimal price;
 private Integer quantity; 
 

}


