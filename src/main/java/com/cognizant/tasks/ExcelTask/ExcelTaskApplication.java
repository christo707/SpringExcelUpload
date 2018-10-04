package com.cognizant.tasks.ExcelTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.cognizant")
@EnableJpaRepositories("com.cognizant.repository")
@EntityScan("com.cognizant.entities")
public class ExcelTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelTaskApplication.class, args);
	}
}
