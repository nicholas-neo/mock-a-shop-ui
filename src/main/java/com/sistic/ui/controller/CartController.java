package com.sistic.ui.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistic.ui.model.Checkout;
import com.sistic.ui.model.Product;
import com.sistic.ui.service.CartService;
import com.sistic.ui.service.ProductService;

@Controller
public class CartController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CartService cartService;	

	@RequestMapping("shopping/add/{productId}")
	public String addProductToCart(@RequestParam(required = false, name = "type") String type, @PathVariable(name = "productId") int id) {
		
		Product p = productService.getProductById(id);
		cartService.addProductToCart(p);
		
		return "redirect:/?type=" + type;
	}
	
	@RequestMapping("shopping/delete/{productId}")
	public String deleteProductInCart(@RequestParam(required = false, name = "type") String type, @PathVariable(name = "productId") int id) {
		
		cartService.deleteProductInCart(id);
		
		return "redirect:/?type=" + type;
	}
	
	@RequestMapping("shopping/checkout")
	public String checkout(Model model) {
		
		Map<Integer, Product> carts = cartService.getShoppingCart();
		
		model.addAttribute("customer", new Checkout());
		model.addAttribute("carts", carts);
		
		return "checkout";
	}
	
	@RequestMapping(path = "shopping/checkout", method = RequestMethod.POST)
	public String checkoutAction(@ModelAttribute Checkout customer, RedirectAttributes redirectAttrs) {
		
		System.out.println(customer);
		String trxId = cartService.confirmCheckout(customer);
		
		redirectAttrs.addFlashAttribute("trxid", trxId);
		
		return "redirect:/";
	}
}
