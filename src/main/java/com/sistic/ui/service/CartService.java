package com.sistic.ui.service;

import java.util.Map;

import com.sistic.ui.model.Checkout;
import com.sistic.ui.model.Product;

public interface CartService {
	public Map<Integer, Product> getShoppingCart();
	public void addProductToCart(Product product);
	public void deleteProductInCart(int id);
	
	public String confirmCheckout(Checkout customer);
}
