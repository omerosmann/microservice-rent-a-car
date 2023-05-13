package com.kodlamaio.invertoryservice;

import com.kodlamaio.commonpackage.utils.constants.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage, Paths.Inventory.ServiceBasePackage})
public class InvertoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvertoryServiceApplication.class, args);
    }


}
