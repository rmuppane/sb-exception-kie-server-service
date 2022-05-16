package com.rh.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com.rh"})
@ConfigurationPropertiesScan(basePackages = {"com.rh"})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
    	
    	System.setProperty("org.kie.server.controller.user", "controllerUser" );
        System.setProperty("org.kie.server.controller.pwd", "Pa$$w0rd");
         
         
        SpringApplication.run(Application.class, args);
    }

}