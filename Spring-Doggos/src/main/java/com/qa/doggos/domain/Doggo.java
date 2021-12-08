package com.qa.doggos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // flags class as a table to Spring
public class Doggo {

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto_increment
	private Integer id;
	
	@Column(nullable = false)
	private String diet;
	
	@Column(nullable = false)
	private Integer age;
	
	@Column(nullable = false)
	private String breed;

	// Constructor method
	public Doggo(Integer id, String diet, Integer age, String breed) {
		super();
		this.id = id;
		this.diet = diet;
		this.age = age;
		this.breed = breed;
	}

	public Doggo() {
		super();
	}

	public Doggo(Integer id) {
		super();
		this.id = id;
	}

	// Getters and Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDiet() {
		return diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	@Override
	public String toString() {
		return "Doggo [id=" + id + ", diet=" + diet + ", age=" + age + ", breed=" + breed + "]";
	}

}
