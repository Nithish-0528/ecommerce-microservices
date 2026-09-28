package com.ecommerce.order_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ecommerce.order_service.event.OrderCreatedEvent;

@Service
public class OrderEventProducer {
	
	private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
	
	public OrderEventProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendOrderCreatedEvent(OrderCreatedEvent event) {
		System.out.println("Order created event producer -");
		kafkaTemplate.send("order-created", event);
	}
	
	public void testSent() {
//		OrderCreatedEvent event = new OrderCreatedEvent(10L, 9L, 10, 2L);
//		kafkaTemplate.send("order-created", event);
	}
}