package com.example.demo.animalsAPI.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

	public Optional<Animals> getAnimalByName(String animalName) throws IOException {
		List<Animals> animalsList = getAnimals();

		return animalsList.stream()
				.filter(animal -> animal.getJapaneseName().equals(animalName))
				.findAny();
	}
}