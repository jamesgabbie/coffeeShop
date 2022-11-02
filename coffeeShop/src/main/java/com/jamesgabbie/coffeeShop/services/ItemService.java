package com.jamesgabbie.coffeeShop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamesgabbie.coffeeShop.models.Item;
import com.jamesgabbie.coffeeShop.repositories.ItemRepository;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepo;
	
	// Find All Items
	public List<Item> findAllItems(){
		return itemRepo.findAll();
	}
	
	 // Find Item By ID
  	public Item findItemt(Long id) {
  		Optional<Item> optionalItem= itemRepo.findById(id);
  		if(optionalItem.isPresent()) {
  			return optionalItem.get();
  		} else {
  			return null;
  		}
  	}
	
  	// Add Item
  	public Item addItem(Item item) {
  		return this.itemRepo.save(item);
  	}
  	// Update Item
  	public Item updateItem(Item item) {
  		return this.itemRepo.save(item);
  	}
	
	
  	// Delete All items
  	public void deleteAllItems (List<Item> allItems) {
  		for(Item p : allItems) {
  			itemRepo.deleteById(p.getId());
  		}
  	}
  	
  	// Delete Item By Id
  	public void deleteItem(Long id) {
  		itemRepo.deleteById(id);
  	}
	
	
}
