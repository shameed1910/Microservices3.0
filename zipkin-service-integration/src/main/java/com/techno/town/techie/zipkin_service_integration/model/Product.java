package com.techno.town.techie.zipkin_service_integration.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    private int id;
    private String name;
    private double price;
}
