package com.example.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.orderservice.dto.InventoryResponse;
import com.example.orderservice.dto.OrderLineItemDto;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderLineItem;
import com.example.orderservice.repo.OrderRepo;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private WebClient webClient;
    
    public void placeOrder(OrderRequest orderRequest){
        //populate order attributes 
        Order order = new Order();


        //set order name
        order.setOrderNumber(UUID.randomUUID().toString());
        
        //convert orderlineitem dto  to orderlineitem Object and assign it to a Order lineItem Object
        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemDto()
                                                         .stream()
                                                         .map(this::mapToOrderLineItem)
                                                         .collect(Collectors.toList()); 
        //set orderlineitem list 
        order.setOrderLineList(orderLineItems);
        
        List<String> skuCodes = order.getOrderLineList().stream().map(OrderLineItem::getSkuCode).collect(Collectors.toList());

        //save order(place order to) dB

        //call inventry service and place order if product is in stock
        InventoryResponse[] invenrtyResponses = webClient.get()
                 .uri("http://localhost:8084/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                 .retrieve()
                 .bodyToMono(InventoryResponse[].class)
                 .block();
        
        boolean allProductsInStock = Arrays.stream(invenrtyResponses)
                                     .allMatch(InventoryResponse::isInStock);

        if (allProductsInStock) {
            orderRepo.save(order);
        }
        
        else{
            throw new IllegalArgumentException("no order item in stock please try again!!");
        }

       
                    
    }
    
    private OrderLineItem mapToOrderLineItem(OrderLineItemDto orderLineItemDto){
     
     OrderLineItem orderLineItem = new OrderLineItem();
     orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
     orderLineItem.setPrice(orderLineItemDto.getPrice());
     orderLineItem.setQuantity(orderLineItemDto.getQuantity());   

     return orderLineItem;

    }


    



}
