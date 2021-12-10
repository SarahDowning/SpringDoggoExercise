package com.qa.doggos.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.doggos.domain.Doggo;
import com.qa.doggos.service.DoggoService;

//tells spring this is a controller - REST compliant
@RestController

public class DoggoController {

	private DoggoService service;

	@Autowired // Dependency Injection

	public DoggoController(DoggoService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create") // 201 status code
	public ResponseEntity<Doggo> createDog(@RequestBody Doggo dog) { // pulls the parameter from the body of the request
		Doggo created = this.service.createDog(dog); // gets the last dog created
		ResponseEntity<Doggo> response = new ResponseEntity<Doggo>(created, HttpStatus.CREATED);
		return response;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Doggo>> getAllDogs() {
		return ResponseEntity.ok(this.service.getAllDogs());
	}

	@GetMapping("/get/{id}")
	public Doggo getDog(@PathVariable Integer id) { // id is index because it's an array.
		return this.service.getDog(id);
	}

	@PutMapping("/replace/{id}")
	public ResponseEntity<Doggo> replaceDog(@PathVariable Integer id, @RequestBody Doggo newDog) {
		Doggo body = this.service.replaceDog(id, newDog);
		ResponseEntity<Doggo> response = new ResponseEntity<Doggo>(body, HttpStatus.ACCEPTED); // 202 accepted
		return response;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Doggo> deleteDog(@PathVariable Integer id) {
		ResponseEntity<Doggo> response = new ResponseEntity<Doggo>(HttpStatus.NO_CONTENT);
		this.service.deleteDog(id.intValue());
		return response;
	}

}