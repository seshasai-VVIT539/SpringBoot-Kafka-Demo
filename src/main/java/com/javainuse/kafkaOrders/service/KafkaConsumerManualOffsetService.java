package com.javainuse.kafkaOrders.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import com.javainuse.kafkaOrders.model.OrderEntity;
import com.javainuse.kafkaOrders.util.AppConstants;


@Service
public class KafkaConsumerManualOffsetService implements AcknowledgingMessageListener<String, String> {
	private final Logger logger = LoggerFactory.getLogger(KafkaConsumerManualOffsetService.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private OrderService orderService;

	@Override
	@RetryableTopic(attempts = "3", backoff = @Backoff(delay = 1000, multiplier = 1.0))
	@KafkaListener(topics = AppConstants.TOPIC_NAME_TEST, groupId = AppConstants.GROUP_ID)
	public void onMessage(ConsumerRecord<String, String> data, Acknowledgment acknowledgment) {
		logger.info("Consumed record from Manual offset : " + data);
		String[] arrOfOrderStrings = data.value().split(";");
		OrderEntity order = orderService.getOrderById(Integer.valueOf(arrOfOrderStrings[0]));
		order.setStatus("closed");
		orderService.updateOrder(order.getId(), order);
		acknowledgment.acknowledge();
	}

	@DltHandler
	public void dlt(ConsumerRecord<String, String> data, Acknowledgment acknowledgment) {
		logger.info(String.format("Message sent to DLT -> %s", data.value()));
		this.kafkaTemplate.send(AppConstants.TOPIC_NAME_TEST + "-dlq", data.value().toString());
	}
}
