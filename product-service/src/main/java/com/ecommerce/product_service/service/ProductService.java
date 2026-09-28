package com.ecommerce.product_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.repository.ProductRepository;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
		
	}
	
	public List<Product> getProductsBySearch(String searchName) {
		return productRepository.findByNameContainingIgnoreCase(searchName);
	}
	
	public Product getProductById(Long id) {
	    Optional<Product> product = productRepository.findById(id);
	    if (product.isEmpty()) {
	    	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id " + id );
	    }
	    
	    return product.get();
	}
	
	public void  reduceStockUsingProductId(Long id, Integer orderedStock) {
		
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		
		if (product.getQuantity() < orderedStock) {
			throw new RuntimeException("Insufficient stock");
		}
		
		Integer reducedStock = product.getQuantity() - orderedStock;
		
		
		product.setQuantity(reducedStock);
		
		productRepository.save(product);
		
		System.out.println("Stock Reduced Successfully");
		
	}
	
	
	
	
	
	
	
	
	
	
}