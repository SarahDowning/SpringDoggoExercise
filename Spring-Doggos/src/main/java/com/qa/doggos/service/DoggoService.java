package com.qa.doggos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.qa.doggos.domain.Doggo;

@Service
public class DoggoService {

	private List<Doggo> dogs = new ArrayList<>();

	// Created
	public Doggo createDog(Doggo dog) {
		this.dogs.add(dog);
		Doggo created = this.dogs.get(this.dogs.size() - 1); // gets the last dog created
		return created;

	}
	
	// Get All
	public List<Doggo> getAllDogs() {
		return this.dogs;
	}
	
	// Get By ID
	public Doggo getDog(Integer id) { // id is index because it's an array.
		this.dogs.get(id);
		return this.dogs.get(id);
	}
	
	// Replace by ID
	public Doggo replaceDog(Integer id, Doggo newDog) {
		Doggo replacing = this.dogs.set(id, newDog);
		return replacing;
	}
	
	// Delete by ID
	public void deleteDog(@PathVariable Integer id) {
		this.dogs.remove(id.intValue());
	}
}
