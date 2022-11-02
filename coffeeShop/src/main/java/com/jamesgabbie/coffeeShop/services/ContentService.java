package com.jamesgabbie.coffeeShop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamesgabbie.coffeeShop.models.Content;
import com.jamesgabbie.coffeeShop.repositories.ContentRepository;

@Service
public class ContentService {
	@Autowired
	private ContentRepository cRepo;
	
	// Find All Content
	public List<Content> findAllContents(){
		return cRepo.findAll();
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
}
