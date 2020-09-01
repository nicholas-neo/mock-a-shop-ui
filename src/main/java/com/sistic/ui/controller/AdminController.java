package com.sistic.ui.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistic.ui.model.Product;
import com.sistic.ui.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public String getAdminPage(Model model) {
		List<Product> result = productService.getAllProduct();
		
		model.addAttribute("products", result);
		return "admin/index";
	}
	
	@GetMapping(path={"/add", "/edit/{id}"})
	public String addProduct(Model model, @PathVariable("id") Optional<Integer> id) {
		
		if (id.isPresent()) {
			Product product = productService.getProductById(id.get());
            model.addAttribute("product", product);
        } else {
        	model.addAttribute("product", new Product());
        }
		
		return "admin/add-or-edit";
	}
	
	@PostMapping("/addOrUpdateProduct")
	public String addOrUpdateProduct(Product product) {
		System.out.println("product id " + product.getId());
		if (product.getId() == null) {
			productService.addProduct(product);
		} else {
			productService.updateProduct(product);
		}
		
		return "redirect:/admin";
	}
	
	@GetMapping("delete/{id}")
	public String deleteProduct(@PathVariable("id") int id) {
		productService.deleteProduct(id);
		
		return "redirect:/admin";
	}
}
