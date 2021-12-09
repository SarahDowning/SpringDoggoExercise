package com.qa.doggos.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.qa.doggos.domain.Doggo;

public interface DoggoService {


	// Created
	Doggo createDog(Doggo dog);
	
	// Get All
	List<Doggo> getAllDogs();
	
	// Get By ID
	Doggo getDog(Integer id);
	
	// Replace by ID
	Doggo replaceDog(Integer id, Doggo newDog);
	
	// Delete by ID
	void deleteDog(@PathVariable Integer id);
}
