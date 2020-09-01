package com.sistic.ui.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.sistic.ui.model.CartItem;
import com.sistic.ui.model.Checkout;
import com.sistic.ui.model.Product;
import com.sistic.ui.service.CartService;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService {

	private Map<Integer, Product> shoppingCart = new HashMap<>();
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${backend.url}")
	private String backendUrl;
	
	@Override
	public void addProductToCart(Product product) {
		if (shoppingCart.containsKey(product.getId())) {
			
			Product p = shoppingCart.get(product.getId());
			p.increaseQty();
			
			shoppingCart.replace(product.getId(), p);
        } else {
        	shoppingCart.put(product.getId(), product);
        }
		
	}

	@Override
	public Map<Integer, Product> getShoppingCart() {
		return Collections.unmodifiableMap(shoppingCart);
	}

	@Override
	public void deleteProductInCart(int id) {
		if (shoppingCart.containsKey(id)) {
			shoppingCart.remove(id);
		}
	}

	@Override
	public String confirmCheckout(Checkout checkout) {
		
		List<CartItem> cartItem = new ArrayList<>();
		
		Iterator<Entry<Integer, Product>> iterator = shoppingCart.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, Product> product = iterator.next();
            CartItem i = new CartItem(product.getKey(), product.getValue().getQty());
            cartItem.add(i);
        }
		
		checkout.setCartItem(cartItem);
		
		Checkout result = restTemplate.postForObject(backendUrl + "/api/cart/checkout", checkout, Checkout.class);
		
		shoppingCart.clear();
		
		return result.getTrxId();
	}
}
