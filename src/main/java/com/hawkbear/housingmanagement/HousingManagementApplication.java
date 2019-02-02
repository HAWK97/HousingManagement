package com.hawkbear.housingmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;



@SpringBootApplication
@EnableFeignClients
@MapperScan(basePackages = {"com.hawkbear.housingmanagement.mapper"})
public class HousingManagementApplication {


    public static void main(String[] args) {
        SpringApplication.run(HousingManagementApplication.class, args);
    }

}

