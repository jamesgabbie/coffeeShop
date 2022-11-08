package com.jamesgabbie.coffeeShop.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jamesgabbie.coffeeShop.models.Content;
import com.jamesgabbie.coffeeShop.models.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long>{
	List<Image> findAll();
	Image findImageByPlacement(String placement);
}
