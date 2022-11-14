package com.jamesgabbie.coffeeShop.services;

import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
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
// Find All Items
	public List<Item> getAllItems(){
		return itemRepo.findAll();
	}
	
// Find All By Category
	public List<Item> findAllByCategory(String category){
		return itemRepo.findAllByCategory(category);
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
	
// Generate JSON STRING from all current Items in Database
	public void getJsonItemList (List<Item> allItems) {
		JSONArray jsonAllItems = new JSONArray();
		for(Item i : allItems) {
			JSONObject jsonItem = new JSONObject();
			jsonItem.put("id", i.getId());
			jsonItem.put("type", i.getType());
			jsonItem.put("category", i.getCategory());
			jsonItem.put("name", i.getName());
			jsonItem.put("detail", i.getDetail());
			jsonItem.put("price", i.getPrice());
			jsonItem.put("owner", i.getOwner());
			
			jsonAllItems.put(jsonItem);
		}
		
		String jsonItemList = jsonAllItems.toString();
		System.out.println(jsonItemList);
		
	}
	

}
