package com.jamesgabbie.coffeeShop.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jamesgabbie.coffeeShop.models.Content;
import com.jamesgabbie.coffeeShop.models.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long>{
	List<Item> findAll();
	List<Item> findAllByForContent(Content content);
}
