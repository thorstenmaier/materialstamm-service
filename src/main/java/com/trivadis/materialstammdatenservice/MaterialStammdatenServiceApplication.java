package com.trivadis.materialstammdatenservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.trivadis.materialstammdatenservice")
public class MaterialStammdatenServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaterialStammdatenServiceApplication.class, args);
	}

}
