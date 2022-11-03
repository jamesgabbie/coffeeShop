package com.jamesgabbie.coffeeShop.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jamesgabbie.coffeeShop.models.User;
import com.jamesgabbie.coffeeShop.services.ContentService;
import com.jamesgabbie.coffeeShop.services.UserService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	@Autowired
	private UserService uService;
	@Autowired
	private ContentService cService;

//	GET REQUESTS
	//Dash View
	@GetMapping("/dash")
	public String adminView(Model viewModel, HttpSession session){
		if(session.getAttribute("user__id") == null) {
			return "redirect:/login";
		} else {
			return "dash";
		}
	}
	//Login View
	@GetMapping("/login")
	public String loginView(Model viewModel, HttpSession session) {
		if(session.getAttribute("user__id") != null) {
			return "redirect:/admin/dash";
		} else {
			return "login";
		}
	}
	//Register View
	@GetMapping("/register")
	public String registerView(User user, Model viewModel, HttpSession session) {
		if(session.getAttribute("user__id") != null) {
			return "redirect:/admin";
		} else {
			viewModel.addAttribute("user", user);
			return "register";
		}
	}		
//		- image httpRequest to html page
	
	
	
	
//  POST REQUESTS
	//	REGISTER	
	@PostMapping("/register")
	public String register(@RequestParam("name") String name, @RequestParam("email") String email,
							@RequestParam("password") String password, @RequestParam("passwordConfirmation") String passwordConfirmation,
							HttpSession session, RedirectAttributes redirectAttr) {
		List<String> errors = new ArrayList<String>();
		if(name.length() > 50 || name.length() < 2) {
			errors.add("Name must be between 2 and 50 characters");
		}
		if(name.isBlank() == true) {
			errors.add("Please provide a name");
		}
		
		if(email.isBlank() == true) {
			errors.add("Please provide your email");
		}
		if(!password.equals(passwordConfirmation)) {
			errors.add("Passwords do not match");
		}
		if(password.isBlank() == true) {
			errors.add("Please provde a password");
		}
		if(passwordConfirmation.isBlank() == true && password.length() > 0) {
			errors.add("Please provde a confirmation passworkd");
		}
		
		if(errors.size() > 0) {
			redirectAttr.addFlashAttribute("errors", errors);
			return "redirect:/admin/register";
		} else {
			User newUser = new User();
			newUser.setName(name);
			newUser.setEmail(email);
			newUser.setPassword(uService.hashPass(password));
			newUser.setContent(cService.setDefaultContent(newUser));
			uService.createUser(newUser);
			session.setAttribute("user__id", newUser.getId());
			return "redirect:/admin/login";
		}	
	}
	
	
	// LOGIN
	@GetMapping("/signIn")
	public String signIn(@RequestParam("email")String email, @RequestParam("password")String password, Model viewModel, HttpSession session, RedirectAttributes redirectAttr) {
		ArrayList<String> errors = new ArrayList<String>();
		if(session.getAttribute("user__id")!=null) {
			return "redirect:/admin/dash";
		}
		if(password.isBlank()) {
			errors.add("Please Submit Email & Password");
		}
		if(email.isBlank()) {
			errors.add("Please Submit Email & Password");
		}
		if(errors.size()>0) {
			redirectAttr.addFlashAttribute("errors", errors);
			return "redirect:/admin/login";
		}
		if(this.uService.authenticateUser(email, password) == true) {
			Long loggedId = this.uService.findByEmail(email).getId();
			session.setAttribute("user__id", loggedId);	
			return "redirect:/admin/dash";
		}
		else {
			errors.add("*Invalid Username and Password*");
			redirectAttr.addFlashAttribute("errors", errors);
			return "login";
		}
	}
	
	
	
//		- update password
//		- save images
//		- update/save content
//		- update/save item
//		- add item	
	
// Logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/admin/login";
	}
}
