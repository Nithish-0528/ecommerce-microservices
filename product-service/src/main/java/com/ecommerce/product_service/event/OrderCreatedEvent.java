package com.ecommerce.product_service.event;

import java.util.List;

public class OrderCreatedEvent {
	
	private Long orderId;
	
	private Long userId;
	
	private List<OrderItemEvent> items;
	
	
	public OrderCreatedEvent(Long orderId, Long userId, List<OrderItemEvent> items) {
		this.orderId = orderId;
		this.userId = userId;
		this.items = items;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<OrderItemEvent> getItems() {
		return items;
	}

	public void setItems(List<OrderItemEvent> items) {
		this.items = items;
	}
	
}