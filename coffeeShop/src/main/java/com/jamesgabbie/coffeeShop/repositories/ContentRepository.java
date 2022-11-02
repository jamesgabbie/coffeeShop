package com.jamesgabbie.coffeeShop.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jamesgabbie.coffeeShop.models.Content;

@Repository
public interface ContentRepository extends CrudRepository<Content, Long>{
	List<Content> findAll();
}
