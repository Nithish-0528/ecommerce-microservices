package com.ecommerce.order_service.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import com.ecommerce.order_service.dto.UserResponse;

public interface UserClient {
	
	@GetExchange("/users/{id}")
	public UserResponse getUserById(@PathVariable Long id);
}