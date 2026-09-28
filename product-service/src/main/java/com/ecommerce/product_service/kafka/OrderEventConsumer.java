package com.ecommerce.product_service.kafka;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ecommerce.product_service.event.OrderCreatedEvent;
import com.ecommerce.product_service.event.OrderItemEvent;
import com.ecommerce.product_service.service.ProductService;

@Service
public class OrderEventConsumer {
	
	private final ProductService productService;
	
	public OrderEventConsumer(ProductService productService) {
		this.productService = productService;
	}
	
//	@KafkaListener(topics = "order-created", groupId = "product-service-group")
	public void consumeOrderCreatedEvent(OrderCreatedEvent event) {
		
		System.out.println("Order created event received");
		System.out.println("order id = "+ event.getOrderId());
		System.out.println("user id = "+ event.getUserId());
		
		List<OrderItemEvent> orderedItems = event.getItems();
		
		for(OrderItemEvent orderedItem : orderedItems) {
			System.out.println( "product id "+orderedItem.getProductId());
			System.out.println("Product quantity" + orderedItem.getQuantity());
			productService.reduceStockUsingProductId(orderedItem.getProductId(), orderedItem.getQuantity());
		}
		

	}
	
}