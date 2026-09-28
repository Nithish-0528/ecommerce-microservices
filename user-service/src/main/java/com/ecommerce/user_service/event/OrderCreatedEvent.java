package com.ecommerce.user_service.event;

public class OrderCreatedEvent {
	
	private Long orderId;
	
	private Long productId;
	
	private Integer quantity;
	
	private Long userId;
	
	public OrderCreatedEvent(Long orderId, Long productId, Integer quantity, Long userId) {
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.userId = userId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}