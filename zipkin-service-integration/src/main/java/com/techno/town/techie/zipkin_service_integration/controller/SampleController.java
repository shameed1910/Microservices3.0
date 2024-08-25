package com.techno.town.techie.zipkin_service_integration.controller;


import com.techno.town.techie.zipkin_service_integration.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/sampler")
public class SampleController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping
    public Mono<List<Product>> getProductDetails(){
        return webClientBuilder.build().get()
                .uri("http://localhost:8081/products").retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {});
    }
}
