package com.ecommerce.order_service.config;

import java.awt.image.RescaleOp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.ecommerce.order_service.client.ProductClient;
import com.ecommerce.order_service.client.UserClient;

@Configuration
public class ProductClientConfig {

	@Bean
	public ProductClient getProductClient() {
		
		RestClient restClient = RestClient.builder().baseUrl("http://localhost:8082").build();
		
		RestClientAdapter adapter = RestClientAdapter.create(restClient);
		
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
		
		return factory.createClient(ProductClient.class);
	}
	
	@Bean UserClient getUserClient() {
		
		RestClient restClient = RestClient.builder().baseUrl("http://localhost:8081").build();
		
		RestClientAdapter adapter = RestClientAdapter.create(restClient);
		
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
		
		return factory.createClient(UserClient.class);
		
	}
	
}