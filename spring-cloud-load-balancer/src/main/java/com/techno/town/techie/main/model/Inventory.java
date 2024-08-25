package com.techno.town.techie.main.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Inventory {
    private int productId;
    private String productName;
    private int quantity;
}
