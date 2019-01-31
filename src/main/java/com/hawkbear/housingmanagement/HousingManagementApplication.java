package com.hawkbear.housingmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HousingManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HousingManagementApplication.class, args);
    }

}

