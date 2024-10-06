package com.techno.town.techie.main.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    private int id;
    private String name;
    private double price;
}
