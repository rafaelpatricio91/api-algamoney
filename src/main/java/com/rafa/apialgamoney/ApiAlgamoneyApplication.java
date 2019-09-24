package com.rafa.apialgamoney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.rafa.apialgamoney.config.property.ApiAlgamoneyProperty;

@SpringBootApplication
@EnableConfigurationProperties(ApiAlgamoneyProperty.class)
public class ApiAlgamoneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAlgamoneyApplication.class, args);
	}

}
