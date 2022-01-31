package com.javainuse.kafkaOrders.service;

import java.util.List;

import com.javainuse.kafkaOrders.model.OrderEntity;

public interface OrderService {
	OrderEntity getOrderById(int orderId);

	List<OrderEntity> getAllOrdersEntities();

	OrderEntity addOrder(OrderEntity order);

	OrderEntity updateOrder(int orderId, OrderEntity order);

	OrderEntity deleteOrder(int orderId);
}
