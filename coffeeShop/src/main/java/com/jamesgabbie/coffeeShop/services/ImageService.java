package com.jamesgabbie.coffeeShop.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jamesgabbie.coffeeShop.models.Image;
import com.jamesgabbie.coffeeShop.models.User;
import com.jamesgabbie.coffeeShop.repositories.ImageRepository;

@Service
public class ImageService {
	@Autowired
	private ImageRepository imgRepo;
//	UPLOAD IMAGE
	public Image uploadImage(MultipartFile file, User user, String type) throws IOException  {
		Image newImage = new Image();
		newImage.setFileName(file.getOriginalFilename());
		newImage.setImgData(file.getBytes());
		newImage.setImgSize(file.getSize());
		newImage.setPlacement(type);
		newImage.setUserImages(user);
		return imgRepo.save(newImage);
}

	//Find Image By 'placement' field
	public Image findImageByPlacement(String p) {
		Image i = imgRepo.findImageByPlacement(p);
		return i;
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
