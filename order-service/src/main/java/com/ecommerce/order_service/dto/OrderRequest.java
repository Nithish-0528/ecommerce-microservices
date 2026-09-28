package com.ecommerce.order_service.dto;

import java.util.List;

public class OrderRequest {
	
	private Long userId;
	private List<OrderItemRequest> items;
	private DeliveryAddressRequest deliveryAddress;
	private String paymentMethod;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<OrderItemRequest> getItems() {
		return items;
	}
	public void setItems(List<OrderItemRequest> items) {
		this.items = items;
	}
	public DeliveryAddressRequest getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(DeliveryAddressRequest deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
	
}