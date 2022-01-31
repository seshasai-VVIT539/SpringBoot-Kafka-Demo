package com.javainuse.kafkaOrders.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javainuse.kafkaOrders.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

}
