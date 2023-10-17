package com.example.demo.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.calculator.service.CalculatorService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes({
		"from1", "from2", "from3", "from4",
		"to1", "to2", "to3", "to4",
		"result1", "result2", "result3", "result4"
})

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
	public String calculate(HttpServletRequest request, Model model) {
		for (int i = 1; i <= 4; i++) {
			String fromParam = "from" + i;
			String toParam = "to" + i;
			String resultParam = "result" + i;
			String operationParam = "operation" + i;

			Integer from = changeValue(request, fromParam,
					(Integer) model.getAttribute(fromParam));
			Integer to = changeValue(request, toParam,
					(Integer) model.getAttribute(toParam));

			model.addAttribute(fromParam, from);
			model.addAttribute(toParam, to);

			String operation = request.getParameter(operationParam);

			model.addAttribute(operationParam, operation);

			String result;

			if (operation == null) {
				result = "";

			} else {
				result = String.valueOf(calculatorService.calculator(from, to, operation));
				model.addAttribute(resultParam, result);
			}
		}
		return "calculator";
	}

	private Integer changeValue(HttpServletRequest request, String paramName, Integer defaultValue) {
		String paramValue = request.getParameter(paramName);

		if (paramValue == null || paramValue.isEmpty()) {
			return 0;
		}
		try {
			return Integer.valueOf(paramValue);

		} catch (NumberFormatException e) {
			return 0;
		}
	}
}