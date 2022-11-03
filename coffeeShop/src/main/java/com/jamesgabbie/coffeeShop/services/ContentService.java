package com.jamesgabbie.coffeeShop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamesgabbie.coffeeShop.models.Content;
import com.jamesgabbie.coffeeShop.models.Item;
import com.jamesgabbie.coffeeShop.models.User;
import com.jamesgabbie.coffeeShop.repositories.ContentRepository;
import com.jamesgabbie.coffeeShop.repositories.ImageRepository;
import com.jamesgabbie.coffeeShop.repositories.ItemRepository;
import com.jamesgabbie.coffeeShop.repositories.UserRepository;

@Service
public class ContentService {
	@Autowired
	private ContentRepository cRepo;
	@Autowired
	private ImageRepository imgRepo;
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private UserRepository uRepo;
	
	// Find All Content
	public List<Content> findAllContents(){
		return cRepo.findAll();
	}
	
	public Content findContentByUser(User user) {
		Content c = cRepo.findByOwner(user);
		return c;
	}
	
	 // Find Content By ID
  	public Content findContent(Long id) {
  		Optional<Content> optionalContent = cRepo.findById(id);
  		if(optionalContent.isPresent()) {
  			return optionalContent.get();
  		} else {
  			return null;
  		}
  	}
	
  	// Add Content
  	public Content addContent(Content content) {
  		return this.cRepo.save(content);
  	}
  	// Update Content
  	public Content updateContent(Content content) {
  		return this.cRepo.save(content);
  	}
	
	
  	// Delete All Contents
  	public void deleteAllContents (List<Content> allContents) {
  		for(Content p : allContents) {
  			cRepo.deleteById(p.getId());
  		}
  	}
  	
  	// Delete Content By Id
  	public void deleteContent(Long id) {
  		cRepo.deleteById(id);
  	}
  	
  	//Load default / Reset Content
  	public Content setDefaultContent(User user) {
		Content defContent = new Content();
		// TEXT CONTENT
		defContent.setWelcomeMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Amet, cras sed sapien pretium elementum. Quam nisl vulputate mauris nam ac ullamcorper arcu. Et sed egestas risus congue. Et enim malesuada et. Ipsum dolor sit amet, consectetur adipisc.");
		defContent.setOwnerMessage("We are Job and Anne, the proud owners of ‘De Koffieschenkerij’ since 2013. We are thrilled and so happy to welcome you to our beautiful café.");
		defContent.setContactMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Amet, cras sed sapien pretium elementum. Quam nisl vulputate mauris nam ac ullamcorper arcu. Et sed egestas risus congue. Et enim malesuada et. Ipsum dolor sit amet, consectetur adipisc.");
		
		
		List<Item> defItemList = new ArrayList<Item>();
		// MENU/ITEM CONTENT
		Item coffeeItem = new Item();
  			coffeeItem.setName("espresso");
  			coffeeItem.setCategory("cafe");
  			coffeeItem.setSubCategory("coffee");
  			coffeeItem.setPrice("2.7 | 3.5");
  			defItemList.add(coffeeItem);
		Item teaItem = new Item();
  			teaItem.setName("house blend(s)");
  			teaItem.setCategory("cafe");
  			teaItem.setSubCategory("tea");
  			teaItem.setDetail("mint | ginger-orange");
  			teaItem.setPrice("3.4");
  			defItemList.add(teaItem);
		Item coolerItem = new Item();
  			coolerItem.setName("house soda(s)");
  			coolerItem.setCategory("cafe");
  			coolerItem.setSubCategory("cooler");
  			coolerItem.setDetail("wild-tea | berry | ginger | pear");
  			coolerItem.setPrice("3.5");
  			defItemList.add(coolerItem);
		Item breakfastItem = new Item();
  			breakfastItem.setName("croissant");
  			breakfastItem.setDetail("butter | jam");
  			breakfastItem.setCategory("kitchen");
  			breakfastItem.setSubCategory("breakfast");
  			breakfastItem.setPrice("2.8");
  			defItemList.add(breakfastItem);
		Item sweetItem = new Item();
  			sweetItem.setName("pastel de nata");
  			sweetItem.setCategory("kitchen");
  			sweetItem.setSubCategory("sweet");
  			sweetItem.setPrice("2.9");
  			defItemList.add(sweetItem);
		Item shopItem = new Item();
  			shopItem.setName("#KeepCup");
  			shopItem.setDetail("reuseable  cup");
  			shopItem.setCategory("kitchen");
  			shopItem.setSubCategory("shop");
  			shopItem.setPrice("11.95 | 12.95");
  			defItemList.add(shopItem);
  			
  		// Save Items 		
  		for (Item a : defItemList) {
  			itemRepo.save(a);
  		}
  		//Assign Items To Content Object
  		defContent.setItems(defItemList);
  		defContent.setOwner(user);
  		return defContent;
  	}
}
