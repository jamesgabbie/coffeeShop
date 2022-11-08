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
// Save Item
	public Item saveItem(Item item){
		return itemRepo.save(item);
	}
// Get All Items
	public List<Item> getAllItems(){
		return itemRepo.findAll();
	}
// FIND Item
	public Item findItem(Long id) {
		Optional <Item> optionalItem = itemRepo.findById(id);
		if(optionalItem.isPresent()) {
			return optionalItem.get();
		} else {
			return null;
		}
	}
// Delete Item
	public void deleteItem(Long id) {
		itemRepo.deleteById(id);
	}
	

}
