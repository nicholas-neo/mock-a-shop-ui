package com.sistic.ui.service;

import java.util.List;

import com.sistic.ui.model.Product;

public interface HomeService {
	
	public List<String> getMenu();
	public List<Product> getProduct(String type);
}
