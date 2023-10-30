package com.example.demo.animalsAPI.controller;

import java.io.IOException;
import java.util.List;

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
	public String selectAnimals(Model model) throws IOException {
		List<Animals> animalsList = animalsAPIService.getAnimals();
		
		model.addAttribute("animals", animalsList);

		return "animalsSearch";
	}

	@PostMapping("animalsSearch")
	public String searchAnimal(@RequestParam String animalName, Model model)
			throws IOException {

		List<Animals> foundAnimals = animalsAPIService.getAnimalsName(animalName);

		if (!foundAnimals.isEmpty()) {
			Animals animal = foundAnimals.get(0);

			model.addAttribute("animal", animal);

			return "animalsDetails";
		} else {

			return "animalsSearch";
		}
	}
}