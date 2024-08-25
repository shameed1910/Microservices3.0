package com.techno.town.techie.main.controller;


import com.techno.town.techie.main.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private DiscoveryClient discoveryClient;



    private List<Product> products = Arrays.asList(
            new Product(1,"A",10.0),
            new Product(2,"B",20.0),
            new Product(3,"C",400.0),
            new Product(4,"D",500.0)
    );


    @GetMapping
    public List<Product> products(){

        logServiceInstancesDetails();

        return products;
    }

    private void logServiceInstancesDetails() {
        discoveryClient.getInstances("product-service").forEach(serviceInstance -> {
            log.info("InstanceId: {}",serviceInstance.getUri());
            log.info("ServiceId: {}",serviceInstance.getServiceId());
            log.info("Port: {}",serviceInstance.getPort());
            log.info("Host: {}",serviceInstance.getHost());
        });
    }
}
