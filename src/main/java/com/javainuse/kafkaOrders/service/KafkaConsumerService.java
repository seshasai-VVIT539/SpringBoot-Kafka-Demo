package com.javainuse.kafkaOrders.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.javainuse.kafkaOrders.model.OrderEntity;
import com.javainuse.kafkaOrders.util.AppConstants;

//@Service
public class KafkaConsumerService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderService orderService;

	@KafkaListener(topics = AppConstants.TOPIC_NAME_TEST, groupId = AppConstants.GROUP_ID)
	public void consume(String orderReceived) {
		logger.info(String.format("Message received -> %s", orderReceived));
		String[] arrOfOrderStrings = orderReceived.split(";");
		OrderEntity order = orderService.getOrderById(Integer.valueOf(arrOfOrderStrings[0]));
		order.setStatus("closed");
		orderService.updateOrder(order.getId(), order);
	}

}
