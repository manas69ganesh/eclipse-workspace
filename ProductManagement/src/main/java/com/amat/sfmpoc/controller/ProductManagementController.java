package com.amat.sfmpoc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.amat.sfmpoc.beans.GeneralResponseObject;
import com.amat.sfmpoc.beans.Product;
import com.amat.sfmpoc.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ProductManagementController {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ProductService productService;

	@GetMapping("/")
	public ModelAndView indexPage() {
		ModelAndView mav = new ModelAndView("welcome");
		return mav;
	}

	@RequestMapping("/home")
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}

	@RequestMapping("/create-product")
	public String createProductPage(@Valid Product product, BindingResult result, Model model) {
		model.addAttribute("product", product);
		return "create-product";
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/create")
	public ModelAndView createProduct(@ModelAttribute Product product) {
		System.out.println("PRODUCT ===>" + product);
		// call create product api here
		String response = productService.createProduct(product);

		System.out.println("Response from Server ===>" + response);

		// get all products and display
		List<Product> productList = new ArrayList<>();
		try {
			GeneralResponseObject responseObject = new ObjectMapper().readValue(response, GeneralResponseObject.class);
			if (responseObject.getData() != null)
				productList = (List<Product>) responseObject.getData();

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("product-data");
		modelAndView.addObject("products", productList);
		return modelAndView;
	}

	@GetMapping("/get-product")
	public String searchProductPage(@Valid Product product, BindingResult result, Model model) {
		model.addAttribute("product", product);
		return "search-product";
	}

	@PostMapping("/search")
	public ModelAndView searchProduct(@ModelAttribute Product product) {

		String response = productService.searchProduct(product.getProductName());

		System.out.println("response from api==>" + response);

		Object productt = null;
		try {
			if(!response.isEmpty()) {
			GeneralResponseObject responseObject = new ObjectMapper().readValue(response, GeneralResponseObject.class);

			if (responseObject.getData() != null)
				productt = responseObject.getData();
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView("search-product");
		modelAndView.addObject("productt", productt);
		return modelAndView;
	}

}
