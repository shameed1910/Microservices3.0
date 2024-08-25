package com.techno.town.techie.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudCloudConfigServerApplication.class, args);
	}

}
