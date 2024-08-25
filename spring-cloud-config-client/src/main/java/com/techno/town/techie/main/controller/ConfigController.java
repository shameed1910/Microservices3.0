package com.techno.town.techie.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
@Slf4j
@RefreshScope
public class ConfigController {

    @Value("${service.name}")
    private String serviceInfo;

    @GetMapping
    public String getServiceInfo(){
        log.info("service name {}",serviceInfo);
        return "service name: "+serviceInfo;
    }
}
