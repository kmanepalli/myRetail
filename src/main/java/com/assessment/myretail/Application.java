package com.assessment.myretail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableMongoRepositories("com.assessment.myretail.repository")
public class Application {
	/*
	 * Making the application executable.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
