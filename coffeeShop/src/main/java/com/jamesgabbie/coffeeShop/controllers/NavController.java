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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jamesgabbie.coffeeShop.models.Content;
import com.jamesgabbie.coffeeShop.models.Image;
import com.jamesgabbie.coffeeShop.models.Item;
import com.jamesgabbie.coffeeShop.models.User;
import com.jamesgabbie.coffeeShop.services.ImageService;
import com.jamesgabbie.coffeeShop.services.UserService;

@Controller
public class NavController {
	@Autowired
	private UserService uService;
	@Autowired
	private ImageService imgService;
//	USER REQUESTS
	@GetMapping("/")
	public String homeView(Model viewModel, HttpSession session) {
		
		// Get Content For HTML
		Content viewContent = new Content();
		
		//Get/Set Owner
		User owner = uService.findUser((long)1);
		viewContent.setWelcomeText(owner.getWelcomeText());

		//Get/Set Owner/Content Images
		List<Image> allImages = owner.getSavedImages();
		Image logoImage = null;
		Image bookendImage = null;
		Image welcomeImage = null;
		Image aboutImage = null;
		Image menuImage1 = null;
		Image menuImage2 = null;
		Image menuImage3 = null;
		Image auxImage = null;
		
		for(Image i : allImages) {
			String p = i.getPlacement();
			if(p.equals("bookend") == true) {
				bookendImage = i;
			} else if(p.equals("logo")==true) {
				logoImage = i;
			} else if(p.equals("welcome")==true) {
				welcomeImage = i;
			} else if(p.equals("about")==true) {
				aboutImage = i;
			} else if(p.equals("menu1")==true) {
				menuImage1 = i;
			} else if(p.equals("menu2")==true) {
				menuImage2 = i;
			} else if(p.equals("menu3")==true) {
				menuImage3 = i;
			} else if(p.equals("contact")==true) {
				auxImage = i;
			}
		}
		
		viewContent.setLogoImage(logoImage);
		viewContent.setBookendImage(bookendImage);
		viewContent.setWelcomeImage(welcomeImage);
		viewContent.setAboutImage(aboutImage);
		viewContent.setMenuImage1(menuImage1);
		viewContent.setMenuImage2(menuImage2);
		viewContent.setMenuImage3(menuImage3);
		viewContent.setAuxImage(auxImage);
		
		//Set Content Text
		viewContent.setContactText(owner.getContactText());
		viewContent.setOwnerText(owner.getAboutText());
		viewContent.setWelcomeText(owner.getWelcomeText());
		
		
		//Get/Set List Items For Menu;
		List<Item> allCoffeeItems = new ArrayList<Item>();
		List<Item> allTeaItems = new ArrayList<Item>();
		List<Item> allCoolerItems = new ArrayList<Item>();
		List<Item> allBreakfastItems = new ArrayList<Item>();
		List<Item> allSweetItems = new ArrayList<Item>();
		List<Item> allShopItems = new ArrayList<Item>();
		
		for(Item i : owner.getItems()) {
			String c = i.getCategory();
			if(c.equals("coffee")==true || c.equals("Coffee")==true){
				allCoffeeItems.add(i);
			} else if(c.equals("tea")==true || c.equals("Tea")==true) {
				allTeaItems.add(i);
			} else if(c.equals("cooler")==true || c.equals("Cooler")==true) {
				allCoolerItems.add(i);
			} else if(c.equals("breakfast")==true) {
				allBreakfastItems.add(i);
			} else if(c.equals("sweet")==true) {
				allSweetItems.add(i);
			} else if(c.equals("shop")==true) {
				allShopItems.add(i);
			}
		}
		viewContent.setAllCoffeeItems(allCoffeeItems);
		viewContent.setAllTeaItems(allTeaItems);
		viewContent.setAllCoolerItems(allCoolerItems);
		viewContent.setAllBreakfastItems(allBreakfastItems);
		viewContent.setAllSweetItems(allSweetItems);
		viewContent.setAllShopItems(allShopItems);
		viewContent.setUpdatedAt(owner.getUpdatedAt());
		
		//Send To View/HTML
		viewModel.addAttribute("content", viewContent);
		
		
		return "index";
	}

	
	//IMG SERVLET RESPONSE/RETRIEVAL
	@RequestMapping(value="getContentImg/{id}")
	public @ResponseBody void getIMG(@PathVariable("id")Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
			Image image = imgService.findImage(id);        
		    response.setContentType("image/jpeg, image/jpg, image/png, image/gif, img/webp");
		    response.getOutputStream().write(image.getImgData());
		    response.getOutputStream().close();
	}
	
	

}
