package com.sistic.ui.service;

import java.util.List;

import com.sistic.ui.model.Product;

public interface ProductService {
	public List<Product> getAllProduct();
	public Product getProductById(int id);
	public Product addProduct(Product product);
	public Product updateProduct(Product product);
	public int deleteProduct(int id);
}
