package com.techno.town.techie.main.configuration;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class InventoryConfiguration {

    @Bean
    @Primary
    ServiceInstanceListSupplier supplier(){
        return new ProductServiceInstanceSupplier("product-service");
    }
}
class ProductServiceInstanceSupplier implements ServiceInstanceListSupplier{

    private String serviceId;

    ProductServiceInstanceSupplier(String serviceId){
        this.serviceId= serviceId;
    }


    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays
                .asList(new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 8081, false),
                        new DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 8082, false)));
    }
}
