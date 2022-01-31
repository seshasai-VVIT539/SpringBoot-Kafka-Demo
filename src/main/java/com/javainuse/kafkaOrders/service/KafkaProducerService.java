package com.javainuse.kafkaOrders.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.javainuse.kafkaOrders.model.OrderEntity;
import com.javainuse.kafkaOrders.util.AppConstants;

@Service
public class KafkaProducerService {
	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private OrderService orderService;

	public OrderEntity sendMessage(OrderEntity order) throws Exception {
		order.setStatus("open");
		OrderEntity ordersaved = orderService.addOrder(order);
		ListenableFuture<SendResult<String, String>> futureListener = kafkaTemplate.send(AppConstants.TOPIC_NAME_TEST,
				ordersaved.toString());
		try {
			SendResult<String, String> sendResult = futureListener.get(10, TimeUnit.SECONDS);
		} catch (Exception ex) {
			logger.error("Failed to send message : " + order);
			throw new Exception(ex.getMessage());
		}
		return ordersaved;
	}
}
