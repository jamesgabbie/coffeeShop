package com.jamesgabbie.coffeeShop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamesgabbie.coffeeShop.models.Image;
import com.jamesgabbie.coffeeShop.repositories.ImageRepository;

@Service
public class ImageService {
	@Autowired
	private ImageRepository imgRepo;
//	Upload Images and save for project
	public Image uploadImage(Image image){
		return imgRepo.save(image);
	}
		
	public List<Image> getAllImages(){
		return imgRepo.findAll();
	}
				
	public Image findImage(Long id) {
		Optional <Image> optionalImage = imgRepo.findById(id);
		if(optionalImage.isPresent()) {
			return optionalImage.get();
		} else {
			return null;
		}
	}
	
// Delete Image
	public void deleteImage(Long id) {
		imgRepo.deleteById(id);
	}
}
