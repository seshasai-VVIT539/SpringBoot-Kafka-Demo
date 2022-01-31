package com.javainuse.kafkaOrders.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.javainuse.kafkaOrders.model.OrderEntity;
import com.javainuse.kafkaOrders.util.AppConstants;

@Service
public class KafkaProducerService {
	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private OrderService orderService;

	public OrderEntity sendMessage(OrderEntity order) {
		logger.info(String.format("Message sent -> %s", order.toString()));
		kafkaTemplate.send(AppConstants.TOPIC_NAME_TEST, order.toString());
		order.setStatus("open");
		return orderService.addOrder(order);
	}
}
