package com.javainuse.kafkaOrders.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class KafkaProducerControllerAdvice {
	@ExceptionHandler(Exception.class)
	public final void handleAllExceptions(Exception ex) {
		String responseBody = ex.getMessage();
		System.out.println("\n\nn Error occured : " + responseBody);
	}
}
