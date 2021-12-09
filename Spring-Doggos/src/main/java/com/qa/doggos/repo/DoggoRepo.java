package com.qa.doggos.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.doggos.domain.Doggo;

public interface DoggoRepo extends JpaRepository<Doggo, Integer> {

}
