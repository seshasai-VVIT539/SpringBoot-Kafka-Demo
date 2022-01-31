package com.javainuse.kafkaOrders.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class KafkaProducerControllerAdvice {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("error", ex.getMessage());
		return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
	}
}
