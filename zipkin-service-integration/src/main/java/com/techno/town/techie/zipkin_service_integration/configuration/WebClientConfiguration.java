package com.techno.town.techie.zipkin_service_integration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {


    @Bean
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }
}
