package com.example.eshop.model.dto;

import lombok.Data;

@Data
public class CustomerOrder {
    private Long customerId;
    private Long orderId;
    private Long productId;

    private String customerName;
    private String customerEmail;
    private String productName;
    private int quantity;
    private int price;
}
