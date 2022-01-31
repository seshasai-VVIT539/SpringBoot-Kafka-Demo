package com.javainuse.kafkaOrders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.kafkaOrders.model.OrderEntity;
import com.javainuse.kafkaOrders.service.KafkaProducerService;
import com.javainuse.kafkaOrders.service.OrderService;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducerController {
	@Autowired
	private KafkaProducerService producerService;
	
	@Autowired
	private OrderService orderService;

	@PostMapping(value = "/publish")
	public OrderEntity sendMessageToKafkaTopic(@RequestParam("message") String message,
			@RequestBody OrderEntity order) {
		return this.producerService.sendMessage(new OrderEntity(order.getId(), order.getDesc(), order.getStatus()));
	}

	@GetMapping(value = "/consume/{id}")
	public OrderEntity getOrderById(@PathVariable int id) {
		return orderService.getOrderById(id);
	}
}
