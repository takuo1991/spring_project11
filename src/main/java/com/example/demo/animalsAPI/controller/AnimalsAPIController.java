package com.example.demo.animalsAPI.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.animalsAPI.data.Animals;
import com.example.demo.animalsAPI.service.AnimalsAPIService;

@Controller
public class AnimalsAPIController {
	private final AnimalsAPIService animalsAPIService;

	public AnimalsAPIController(AnimalsAPIService animalsAPIService) {
		this.animalsAPIService = animalsAPIService;
	}

	@GetMapping("animalsSearch")
	public String getPets(Model model) throws IOException {

		List<Animals> animalsList = animalsAPIService.getAnimals();

		model.addAttribute("animalsList", animalsList);

		return "animalsSearch.html";
	}

	@PostMapping("animalsSearch")
	public String searchAnimal(@RequestParam String animalName, Model model)
			throws IOException {

		Optional<Animals> optionalAnimal = animalsAPIService.getAnimalByName(animalName);

		if (optionalAnimal.isPresent()) {
			model.addAttribute("animal", optionalAnimal.get());

			return "animalsDetails";
		} else {

			return "animalsSearch";
		}
	}
}