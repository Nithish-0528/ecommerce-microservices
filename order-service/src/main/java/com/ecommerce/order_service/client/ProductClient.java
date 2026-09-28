package com.ecommerce.order_service.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import com.ecommerce.order_service.dto.ProductResponse;

public interface ProductClient {
	
	@GetExchange("/products/{id}")
	public ProductResponse getProductById(@PathVariable Long id);
	
}