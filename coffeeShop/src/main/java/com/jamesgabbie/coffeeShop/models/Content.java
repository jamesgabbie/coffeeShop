package com.jamesgabbie.coffeeShop.models;

import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class Content {
	private User owner;
	private String welcomeText;
	private String ownerText;
	private String contactText;
	private List<Item> allItems;
	private List<Item> cafeItems;
	private List<Item> kitchenItems;
	private List<Image> allImages;
	private Image titleImage;
	private Image welcomeImage;
	private Image aboutImage;
	private Image menuImage1;
	private Image menuImage2;
	private Image menuImage3;
 
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

	public List<Item> getAllItems() {
		return allItems;
	}

	public void setAllItems(List<Item> allItems) {
		this.allItems = allItems;
	}

	public List<Item> getCafeItems() {
		return cafeItems;
	}

	public void setCafeItems(List<Item> cafeItems) {
		this.cafeItems = cafeItems;
	}

	public List<Item> getKitchenItems() {
		return kitchenItems;
	}

	public void setKitchenItems(List<Item> kitchenItems) {
		this.kitchenItems = kitchenItems;
	}

	public List<Image> getAllImages() {
		return allImages;
	}

	public void setAllImages(List<Image> allImages) {
		this.allImages = allImages;
	}

	public Image getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(Image titleImage) {
		this.titleImage = titleImage;
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

	public Content(User owner, String welcomeText, String ownerText, String contactText, List<Item> allItems,
			List<Item> cafeItems, List<Item> kitchenItems, List<Image> allImages, Image titleImage, Image welcomeImage,
			Image aboutImage, Image menuImage1, Image menuImage2, Image menuImage3) {
		this.owner = owner;
		this.welcomeText = welcomeText;
		this.ownerText = ownerText;
		this.contactText = contactText;
		this.allItems = allItems;
		this.cafeItems = cafeItems;
		this.kitchenItems = kitchenItems;
		this.allImages = allImages;
		this.titleImage = titleImage;
		this.welcomeImage = welcomeImage;
		this.aboutImage = aboutImage;
		this.menuImage1 = menuImage1;
		this.menuImage2 = menuImage2;
		this.menuImage3 = menuImage3;
	}


}
