package com.jamesgabbie.coffeeShop.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jamesgabbie.coffeeShop.models.Image;
import com.jamesgabbie.coffeeShop.models.Item;
import com.jamesgabbie.coffeeShop.models.User;
import com.jamesgabbie.coffeeShop.services.ImageService;
import com.jamesgabbie.coffeeShop.services.ItemService;
import com.jamesgabbie.coffeeShop.services.UserService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	@Autowired
	private UserService uService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ImageService imgService;

//	GET REQUESTS
	//Dash View
	@GetMapping("/dash")
	public String adminView(Model viewModel, HttpSession session){
		if(session.getAttribute("user__id") == null) {
			return "redirect:/admin/login";
		} else {
			User user = uService.findUser((long)session.getAttribute("user__id"));
			viewModel.addAttribute("user", user);
			
			List<Item> allCoffeeItems = itemService.findAllByCategory("coffee");
			viewModel.addAttribute("allCoffeeItems", allCoffeeItems);
			List<Item> allTeaItems = itemService.findAllByCategory("tea");
			viewModel.addAttribute("allTeaItems", allTeaItems);
			List<Item> allCoolerItems = itemService.findAllByCategory("cooler");
			viewModel.addAttribute("allCoolerItems", allCoolerItems);
			List<Item> allBreakfastItems = itemService.findAllByCategory("breakfast");
			viewModel.addAttribute("allBreakfastItems", allBreakfastItems);
			List<Item> allSweetItems = itemService.findAllByCategory("sweet");
			viewModel.addAttribute("allSweetItems", allSweetItems);
			List<Item> allShopItems = itemService.findAllByCategory("shop");
			viewModel.addAttribute("allShopItems", allShopItems);
						
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
			return "redirect:/admin/dash";
		} else {
			viewModel.addAttribute("user", user);
			return "register";
		}
	}		
//		- image httpRequest to html page
	
	
	
	
//  ACTION/POST REQUESTS
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
			newUser.setWelcomeText(uService.getDefaultText("welcome"));
			newUser.setAboutText(uService.getDefaultText("about"));
			newUser.setContactText(uService.getDefaultText("contact"));
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
	
	// Update Content 	
	@PostMapping("/updateContent/{type}")
	public String addItem(@PathVariable("type") String type, @RequestParam("text") String text, HttpSession session){

		if(session.getAttribute("user__id") == null) {
			return "redirect:/admin/login";
		}
		
		
		User owner = uService.findUser((long)session.getAttribute("user__id"));
		if(type.equals("welcome")) {
			owner.setWelcomeText(text);
		} else if(type.equals("about")) {
			owner.setAboutText(text);
		} else if(type.equals("contact")) {
			owner.setContactText(text);
		}
		
		uService.updateUser(owner.getId());
		return "redirect:/admin/dash";
	}
	
//	Load Default Content
	@GetMapping("/loadDefaultTextContent")
	public String loadDefTextCont(HttpSession session) {
		Long uId = (Long)session.getAttribute("user__id");
		User user = uService.findUser(uId);
		user.setWelcomeText(uService.getDefaultText("welcome"));
		user.setAboutText(uService.getDefaultText("about"));
		user.setContactText(uService.getDefaultText("contact"));
		uService.updateUser(uId);
		
		return "redirect:/admin/dash";
	}
	
	// Add Content Item 	
	@PostMapping("/addItem")
	public String addItem(@RequestParam("type")String type, @RequestParam("name")String name,
							@RequestParam("category")String category, @RequestParam("detail")String detail,
							@RequestParam("price")String price, HttpSession session){

		if(session.getAttribute("user__id") == null) {
			return "redirect:/admin/login";
		}
		
		User owner = uService.findUser((long)session.getAttribute("user__id"));
		Item newItem = new Item();
		newItem.setType(type);
		newItem.setName(name);
		newItem.setCategory(category);
		newItem.setPrice(price);
		if(detail.isBlank() != true) {
			newItem.setDetail(detail);
		}
		newItem.setOwner(owner);
		itemService.saveItem(newItem);
		
		return "redirect:/admin/dash";
	}
	
	// Update Content Item 	
	@PostMapping("/updateItem/{id}")
	public String updateItem(@PathVariable("id")Long id, @RequestParam("type")String type, @RequestParam("name")String name,
							@RequestParam("category")String category, @RequestParam("detail")String detail,
							@RequestParam("price")String price, HttpSession session){

		if(session.getAttribute("user__id") == null) {
			return "redirect:/admin/login";
		}
		
		User owner = uService.findUser((long)session.getAttribute("user__id"));
		Item item = itemService.findItem(id);
		item.setType(type);
		item.setCategory(category);
		item.setName(name);
		item.setPrice(price);
		if(detail.isBlank() != true) {
			item.setDetail(detail);
		}
		item.setOwner(owner);
		itemService.saveItem(item);
		
		return "redirect:/admin/dash";
	}
	
	
	//DeleteItem
	@GetMapping("/deleteItem/{id}")
	public String deleteItem(@PathVariable("id")Long id, HttpSession session) {
		if(session.getAttribute("user__id") == null) {
			return "redirect:/admin/login";
		}
		
		itemService.deleteItem(id);
		
		
		return "redirect:/admin/dash";
	}
	
	
//	Add Content Image
	@PostMapping("/updateContentImage/{type}")
	public String uploadUserImage(@PathVariable("type") String type, @RequestParam("file") MultipartFile file, HttpSession session, RedirectAttributes redirectAttr) throws IOException {
		if(session.getAttribute("user__id")== null) {
			return "redirect:/admin/login";
		}
		Long userId = (Long)session.getAttribute("user__id");
		User loggedUser = this.uService.findUser(userId);
		
		List<Image> allUserImages = loggedUser.getSavedImages();
		for(Image img : allUserImages) {
			if(img.getPlacement().equals(type)==true) {
				imgService.deleteImage(img.getId());
			}
		}
		
		
		
		imgService.uploadImage(file, loggedUser, type);
		
		
		
		return "redirect:/admin/dash";
	}
	
	
	//Change User/Admin Password
	@PostMapping("/changePassword")
	public String changePassword(@RequestParam("currentPassword") String currPass, @RequestParam("newPassword")String newPass,
									@RequestParam("newPasswordConfirm") String newPassConfirm, HttpSession session, RedirectAttributes redirectAttr) {
		if(session.getAttribute("user__id") == null) {
			return "redirect:/admin/login";
		} else {
			List<String> errors = new ArrayList<String>();
			Long userId = (long)session.getAttribute("user__id");
			User user = uService.findUser(userId);
			
			if(uService.authenticateUser(user.getEmail(), currPass) == false) {
				errors.add("failed: password incorrect");
			}
			if(newPassConfirm.equals(newPass) == false) {
				errors.add("failed: new passwords do not match");
			}
			if(currPass.isBlank() == true || newPass.isBlank() == true || newPassConfirm.isBlank() == true) {
				errors.add("failed: all fields required");
			}
			if(errors.size() > 0) {
				redirectAttr.addFlashAttribute("errors", errors);
				return "redirect:/admin/dash";
			} else {
				user.setPassword(uService.hashPass(newPass));
				uService.updateUser(user.getId());
				redirectAttr.addFlashAttribute("success", "success: password changed");
				return "redirect:/admin/dash";
			}
		}
	}
	
	
	//IMG SERVLET RESPONSE/RETRIEVAL
	@RequestMapping(value="getIMG/{placement}")
	public @ResponseBody void getIMG(@PathVariable("placement")String placement, HttpServletRequest request, HttpServletResponse response) throws IOException {
			Image image = imgService.findImageByPlacement(placement);        
		    response.setContentType("image/jpeg, image/jpg, image/png, image/gif, img/webp");
		    response.getOutputStream().write(image.getImgData());
		    response.getOutputStream().close();
	}
	
	
//		- update password
//		- update/save item
//		- add item	
	
// Logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/admin/login";
	}
}
