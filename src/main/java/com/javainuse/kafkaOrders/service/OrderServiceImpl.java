package com.javainuse.kafkaOrders.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javainuse.kafkaOrders.model.OrderEntity;
import com.javainuse.kafkaOrders.repo.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public OrderEntity getOrderById(int orderId) {
		Optional<OrderEntity> order = orderRepository.findById(orderId);
		return order.get();
	}

	@Override
	public List<OrderEntity> getAllOrdersEntities() {
		return orderRepository.findAll();
	}

	@Override
	public OrderEntity addOrder(OrderEntity order) {
		OrderEntity savedOrder = orderRepository.save(order);
		return savedOrder;
	}

	@Override
	public OrderEntity updateOrder(int orderId, OrderEntity order) {
		orderRepository.save(order);
		return orderRepository.getById(orderId);
	}

	@Override
	public OrderEntity deleteOrder(int orderId) {
		Optional<OrderEntity> orderFoundOptional = orderRepository.findById(orderId);
		OrderEntity order = orderFoundOptional.get();
		orderRepository.deleteById(orderId);
		return order;
	}

}
