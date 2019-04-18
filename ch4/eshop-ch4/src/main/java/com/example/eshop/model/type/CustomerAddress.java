package com.example.eshop.model.type;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerAddress implements Serializable {
    private String streetAddress;
    private String city;
    private String country;
}
