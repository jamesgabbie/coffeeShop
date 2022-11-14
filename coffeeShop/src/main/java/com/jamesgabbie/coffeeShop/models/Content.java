package com.jamesgabbie.coffeeShop.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;


@Component
public class Content {
	private User owner;
	private String welcomeText;
	private String ownerText;
	private String contactText;
	private List<Item> allCoffeeItems;
	private List<Item> allTeaItems;
	private List<Item> allCoolerItems;
	private List<Item> allBreakfastItems;
	private List<Item> allSweetItems;
	private List<Item> allShopItems;
	private Image logoImage;
	private Image bookendImage;
	private Image welcomeImage;
	private Image aboutImage;
	private Image menuImage1;
	private Image menuImage2;
	private Image menuImage3;
	private Image auxImage;
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
 
	public Content() {
		
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getWelcomeText() {
		return welcomeText;
	}

	public void setWelcomeText(String welcomeText) {
		this.welcomeText = welcomeText;
	}

	public String getOwnerText() {
		return ownerText;
	}

	public void setOwnerText(String ownerText) {
		this.ownerText = ownerText;
	}

	public String getContactText() {
		return contactText;
	}

	public void setContactText(String contactText) {
		this.contactText = contactText;
	}

	public List<Item> getAllCoffeeItems() {
		return allCoffeeItems;
	}

	public void setAllCoffeeItems(List<Item> allCoffeeItems) {
		this.allCoffeeItems = allCoffeeItems;
	}

	public List<Item> getAllTeaItems() {
		return allTeaItems;
	}

	public void setAllTeaItems(List<Item> allTeaItems) {
		this.allTeaItems = allTeaItems;
	}

	public List<Item> getAllCoolerItems() {
		return allCoolerItems;
	}

	public void setAllCoolerItems(List<Item> allCoolerItems) {
		this.allCoolerItems = allCoolerItems;
	}

	public List<Item> getAllBreakfastItems() {
		return allBreakfastItems;
	}

	public void setAllBreakfastItems(List<Item> allBreakfastItems) {
		this.allBreakfastItems = allBreakfastItems;
	}

	public List<Item> getAllSweetItems() {
		return allSweetItems;
	}

	public void setAllSweetItems(List<Item> allSweetItems) {
		this.allSweetItems = allSweetItems;
	}

	public List<Item> getAllShopItems() {
		return allShopItems;
	}

	public void setAllShopItems(List<Item> allShopItems) {
		this.allShopItems = allShopItems;
	}

	public Image getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(Image logoImage) {
		this.logoImage = logoImage;
	}

	public Image getBookendImage() {
		return bookendImage;
	}

	public void setBookendImage(Image bookendImage) {
		this.bookendImage = bookendImage;
	}

	public Image getWelcomeImage() {
		return welcomeImage;
	}

	public void setWelcomeImage(Image welcomeImage) {
		this.welcomeImage = welcomeImage;
	}

	public Image getAboutImage() {
		return aboutImage;
	}

	public void setAboutImage(Image aboutImage) {
		this.aboutImage = aboutImage;
	}

	public Image getMenuImage1() {
		return menuImage1;
	}

	public void setMenuImage1(Image menuImage1) {
		this.menuImage1 = menuImage1;
	}

	public Image getMenuImage2() {
		return menuImage2;
	}

	public void setMenuImage2(Image menuImage2) {
		this.menuImage2 = menuImage2;
	}

	public Image getMenuImage3() {
		return menuImage3;
	}

	public void setMenuImage3(Image menuImage3) {
		this.menuImage3 = menuImage3;
	}
	

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Image getAuxImage() {
		return auxImage;
	}

	public void setAuxImage(Image auxImage) {
		this.auxImage = auxImage;
	}

}
