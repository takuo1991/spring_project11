package com.example.demo.animalsAPI.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.animalsAPI.data.Animals;
import com.example.demo.animalsAPI.repository.AnimalsRepository;

@Service
public class AnimalsAPIService {

	private final AnimalsRepository animalsRepository;

	public AnimalsAPIService(AnimalsRepository animalsRepository) {
		this.animalsRepository = animalsRepository;
	}
	
	public List<Animals> getAnimals() throws IOException {

		Animals[] animalsList = animalsRepository.getAnimals();

		return Arrays.asList(animalsList);
	}

	public List<Animals> getAnimalsName(String animalsName) throws IOException {
		Animals[] animalsList = animalsRepository.getAnimals();

		List<Animals> foundAnimals = new ArrayList<>();

		for (Animals animal : animalsList) {
			if (animal.getJapaneseName().equals(animalsName)) {
				foundAnimals.add(animal);
			}
		}
		return foundAnimals;
	}
}