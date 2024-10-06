package com.techno.town.techie.main.controller;


import com.techno.town.techie.main.model.Product;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
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

    private final Timer customTimer;
    private final Counter customCounter;

    public ProductController(MeterRegistry meterRegistry) {
        this.customTimer = meterRegistry.timer("custom_product_fetch_duration");
        this.customCounter = meterRegistry.counter("custom-product_counter");
    }
    @Autowired
    private DiscoveryClient discoveryClient;



    private List<Product> products = Arrays.asList(
            new Product(1,"A",10.0),
            new Product(2,"B",20.0),
            new Product(3,"C",400.0),
            new Product(4,"D",500.0)
    );




    @GetMapping
    public List<Product> products() {
        //measure duration of the method execution
        return customTimer.record(()->{
            logServiceInstancesDetails();
            //Increment the counter
            customCounter.increment();
            return products;
        });
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
