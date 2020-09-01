package com.sistic.ui.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sistic.ui.model.Product;
import com.sistic.ui.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${backend.url}")
	private String backendUrl;
	
	
	@Override
	public List<String> getMenu() {
		
		ResponseEntity<String[]> menus = restTemplate.getForEntity(backendUrl + "/api/menu", String[].class);
		
		return Arrays.asList(menus.getBody());
	}


	@Override
	public List<Product> getProduct(String type) {
		ResponseEntity<List<Product>> menus;
		if (type == null || type.isEmpty()) {
			menus = restTemplate.exchange(backendUrl + "/api/products", HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {});
		} else {
			menus = restTemplate.exchange(backendUrl + "/api/products/item_type/" + type, HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {});
		}
		
		return menus.getBody();
	}

}
