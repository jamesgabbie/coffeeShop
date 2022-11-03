package com.jamesgabbie.coffeeShop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {
//	USER REQUESTS
	@GetMapping("/")
	public String homeView() {
		return "index";
	}
	
	

	

}
