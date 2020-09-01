package com.sistic.ui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sistic.ui.model.Product;
import com.sistic.ui.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${backend.url}")
	private String backendUrl;
	
	@Override
	public Product getProductById(int id) {
		Product product = restTemplate.getForObject(backendUrl + "/api/products/" + id, Product.class);
		
		return product;
	}

	@Override
	public List<Product> getAllProduct() {
		ParameterizedTypeReference<List<Product>> responseType = new ParameterizedTypeReference<List<Product>>() {};
		ResponseEntity<List<Product>> response = restTemplate.exchange(backendUrl + "/api/products/", HttpMethod.GET, null, responseType);
		return response.getBody();
	}

	@Override
	public Product addProduct(Product product) {
		Product result = restTemplate.postForObject(backendUrl + "/api/products", product, Product.class);
		return result;
	}

	@Override
	public Product updateProduct(Product product) {
		restTemplate.put(backendUrl + "/api/products/" + product.getId(), product);
		return null;
	}

	@Override
	public int deleteProduct(int id) {
		restTemplate.delete(backendUrl + "/api/products/" + id);
		return 0;
	}
}
