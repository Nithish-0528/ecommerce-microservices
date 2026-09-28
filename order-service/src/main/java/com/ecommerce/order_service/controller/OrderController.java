package com.ecommerce.order_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.order_service.dto.OrderRequest;
import com.ecommerce.order_service.dto.ProductResponse;
import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.kafka.OrderEventProducer;
import com.ecommerce.order_service.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private final OrderService orderService;
	private final OrderEventProducer orderEventProducer;
	
	public OrderController(OrderService orderService , OrderEventProducer orderEventProducer) {
		this.orderService = orderService;
		this.orderEventProducer = orderEventProducer;
	}
	
	@GetMapping
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}
	
	@PostMapping
	public ResponseEntity<?> createOrder(@RequestBody OrderRequest order) {
		try {
			return ResponseEntity.ok(orderService.createOrder(order)); 
		}
		//This block is used to catch the exception thrown from other services ie, user client, product client
//		catch(HttpClientErrorException e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product is not found from order controller");
//		}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
	
	@PutMapping
	public Order updateOrder(@RequestBody Order order) {
		
		return orderService.updateOrder(order);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
		
		return ResponseEntity.ok("Order Deleted Successfully");
	}
	
	@GetMapping("/products/{id}")
	public ProductResponse getProductById(@PathVariable Long id ) {
		return orderService.getProductById(id);
	}
	
	@PostMapping("/kafkatest")
	public String kafkaEventTest() {
		
		orderEventProducer.testSent();
		return "Test Worked";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}