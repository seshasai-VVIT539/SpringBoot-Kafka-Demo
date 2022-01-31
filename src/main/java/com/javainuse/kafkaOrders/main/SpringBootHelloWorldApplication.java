package com.javainuse.kafkaOrders.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.javainuse.kafkaOrders" })
@EntityScan("com.javainuse.kafkaOrders.model")
@EnableJpaRepositories("com.javainuse.kafkaOrders.repo")
@ComponentScan(basePackages = { "com.javainuse.kafkaOrders" })
public class SpringBootHelloWorldApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootHelloWorldApplication.class, args);
	}
}