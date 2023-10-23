package com.example.demo.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.calculator.service.CalculatorService;

@Controller
public class CalculatorController {

	private final CalculatorService calculatorService;

	public CalculatorController(CalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}

	@GetMapping("calculator")
	public String showForm() {

		return "calculator";
	}

	@PostMapping("calculator")
	public String calculate(@RequestParam int from,
			@RequestParam int to, String operation, Model model) {

		int result = calculatorService.calculator(from, to, operation);

		model.addAttribute("result", result);

		return "calculator";
	}
}