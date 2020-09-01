package com.sistic.ui.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistic.ui.model.Product;
import com.sistic.ui.service.CartService;
import com.sistic.ui.service.HomeService;

@Controller
public class ViewController {
	
	@Autowired
	private HomeService homeService;
	
	@Autowired
	private CartService cartService;

	@RequestMapping("/")
	public String home(@RequestParam(required = false, name = "type") String type, Model model) {
		
		List<String> menus = homeService.getMenu();
		List<Product> products = homeService.getProduct(type);
		Map<Integer, Product> carts = cartService.getShoppingCart();
		
		model.addAttribute("menu", menus);
		model.addAttribute("products", products);
		model.addAttribute("carts", carts);
		
		return "home";
	}
}
