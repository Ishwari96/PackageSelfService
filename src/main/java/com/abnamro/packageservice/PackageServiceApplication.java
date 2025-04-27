package com.abnamro.packageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.abnamro.packageservice.repository")
@EnableFeignClients(basePackages = "com.abnamro.packageservice.client")
public class PackageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PackageServiceApplication.class, args);
    }

}
