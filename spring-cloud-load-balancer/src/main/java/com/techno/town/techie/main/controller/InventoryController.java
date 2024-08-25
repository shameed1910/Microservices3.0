package com.techno.town.techie.main.controller;

import com.techno.town.techie.main.model.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {


    @Autowired
    private WebClient.Builder loadBalancedWebClientBuilder;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    @CircuitBreaker(name = "inventory-service", fallbackMethod = "fallbackProductDetails")
    public Mono<List<Product>> getProductDetails() {
        return loadBalancedWebClientBuilder.build().get()
                .uri("http://product-service/products")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {});
    }

    public Mono<List<Product>> fallbackProductDetails(Throwable throwable) {
        System.out.println("Fallback method called due to: " + throwable.getMessage());

        List<Product> fallbackList = Collections.singletonList(
                new Product(1, "Fallback Product Description", 200)
        );
        return Mono.just(fallbackList);
    }


}
