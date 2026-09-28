package com.ecommerce.order_service.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.order_service.client.ProductClient;
import com.ecommerce.order_service.client.UserClient;
import com.ecommerce.order_service.dto.OrderItemRequest;
import com.ecommerce.order_service.dto.OrderRequest;
import com.ecommerce.order_service.dto.ProductResponse;
import com.ecommerce.order_service.dto.UserResponse;
import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.entity.OrderItem;
import com.ecommerce.order_service.event.OrderCreatedEvent;
import com.ecommerce.order_service.event.OrderItemEvent;
import com.ecommerce.order_service.kafka.OrderEventProducer;
import com.ecommerce.order_service.repository.OrderItemRepository;
import com.ecommerce.order_service.repository.OrderRepository;

@Service
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final ProductClient productClient;
	private final UserClient userClient;
	private final OrderEventProducer orderEventProducer;
	private final OrderItemRepository orderItemRepository;
	
	public OrderService(OrderRepository orderRepository,
			ProductClient productClient,
			UserClient userClient, 
			OrderEventProducer orderEventProducer,
			OrderItemRepository orderItemRepository) {
		this.orderRepository = orderRepository;
		this.productClient = productClient;
		this.userClient = userClient;
		this.orderEventProducer = orderEventProducer;
		this.orderItemRepository = orderItemRepository;
	}
	
	@Transactional
	public Order createOrder(OrderRequest order) {
		UserResponse user = userClient.getUserById(order.getUserId());
		
		if (user == null || user.getId() == null ) {
			throw new RuntimeException("User Not Found");
		}
		
		List<OrderItemRequest> orderedItems = order.getItems();
		
		Double totalPrice = 0.0;
		
		List<ProductResponse> products = new ArrayList<ProductResponse>();
		
		for(OrderItemRequest orderedItem: orderedItems) {
			ProductResponse product = productClient.getProductById(orderedItem.getProductId());
			if (product == null || product.getQuantity() < orderedItem.getQuantity()) {
				throw new RuntimeException("Order is not created because quantity is not sufficient");
			}
			
			totalPrice += orderedItem.getQuantity() * product.getPrice();
			products.add(product);
		}	
		
		Order createOrder = new Order();
		createOrder.setUserId(order.getUserId());
		createOrder.setCity(order.getDeliveryAddress().getCity());
		createOrder.setName(order.getDeliveryAddress().getName());
		createOrder.setPaymentMethod(order.getPaymentMethod());
		createOrder.setTotalPrice(totalPrice);
		createOrder.setStatus("CREATED");
		
		Order savedOrder = orderRepository.save(createOrder);
		
		List<OrderItemEvent> orderItemEvents = new ArrayList<OrderItemEvent>();
		
		if (savedOrder.getId() != null) {
			for (int i = 0; i < orderedItems.size(); i++) {
				
				OrderItem createdOrderedItem = new OrderItem();
				
				ProductResponse product = products.get(i);
				OrderItemRequest orderedItem = orderedItems.get(i);
				
				createdOrderedItem.setOrderId(savedOrder.getId());
				createdOrderedItem.setProductId(orderedItem.getProductId());
				createdOrderedItem.setQuantity(orderedItem.getQuantity());
				createdOrderedItem.setPrice(product.getPrice());
				orderItemRepository.save(createdOrderedItem);
				
				OrderItemEvent orderItemEvent = new OrderItemEvent(product.getId(), orderedItem.getQuantity());
				orderItemEvents.add(orderItemEvent);
			}
			
		}
		
		OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(savedOrder.getId(), savedOrder.getUserId(), orderItemEvents);
		
//		orderEventProducer.sendOrderCreatedEvent(orderCreatedEvent);
		return savedOrder;
		
	}
	
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}
	
	public Order updateOrder (Order order) {
		Order existingOrder = orderRepository.getById(order.getId());
		
		if (existingOrder.getId() == null) {
			throw new RuntimeException("Order is not present");
		}
		
//		existingOrder.setQuantity(order.getQuantity());
		existingOrder.setTotalPrice(order.getTotalPrice());
//		existingOrder.setProductId(order.getProductId());
		
		return orderRepository.save(existingOrder);
	}
	
	
	public Order updateOrderStatus (Long id, String status) {
		
		Order existingOrder = orderRepository.getById(id);
		
		existingOrder.setStatus(status);
		return existingOrder;	
	}
	
	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}
	
	public ProductResponse getProductById(Long id) {
		
		return productClient.getProductById(id);
		
	}
	
	
	
	
	
	
	
	
	
	
}