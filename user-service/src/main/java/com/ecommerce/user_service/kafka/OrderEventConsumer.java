package com.ecommerce.user_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ecommerce.user_service.event.OrderCreatedEvent;

@Service
public class OrderEventConsumer {
	 
//	@KafkaListener(topics = "order-created-v2", groupId = "user-service-group")
	public void orderCreatedEventConsumer(OrderCreatedEvent event) {
		
		System.out.println("Event received from user service consumer");
		System.out.println("user Id = " + event.getUserId());
		System.out.println("product Id = " + event.getProductId());
		System.out.println("order Id = " + event.getOrderId());
	}
	
}