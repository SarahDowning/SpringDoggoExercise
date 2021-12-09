package com.qa.doggos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.doggos.domain.Doggo;
import com.qa.doggos.repo.DoggoRepo;

@Service
public class DoggoServiceDB implements DoggoService {

	private DoggoRepo repo;

	@Autowired
	public DoggoServiceDB(DoggoRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Doggo createDog(Doggo dog) {
		Doggo created = this.repo.save(dog);
		return created;
	}

	@Override
	public List<Doggo> getAllDogs() {
		return this.repo.findAll();
	}

	@Override
	public Doggo getDog(Integer id) {
		Optional<Doggo> found = this.repo.findById(id);
		return found.get();
	}

	@Override
	public Doggo replaceDog(Integer id, Doggo newDog) {
		Doggo existing = this.repo.findById(id).get();

		existing.setId(newDog.getId());
		existing.setDiet(newDog.getDiet());
		existing.setAge(newDog.getAge());
		existing.setBreed(newDog.getBreed());

		Doggo updated = this.repo.save(existing);

		return updated;
	}

	@Override
	public void deleteDog(Integer id) {
		this.repo.deleteById(id);

	}

}
