package com.javainuse.kafkaOrders.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderEntity {
	@Id
	@GeneratedValue
	private int id;

	private String desc;

	private String status;

	public OrderEntity() {
		super();
	}

	public OrderEntity(String desc, String status) {
		super();
		this.desc = desc;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return id + ";" + desc + ";" + status;
	}

}
