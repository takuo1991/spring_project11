package com.example.demo.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

	public int calculator(int num1, int num2, String operation) {
		int result;
		switch (operation) {
		case "add":
			result = num1 + num2;
			break;
		case "subtract":
			result = num1 - num2;
			break;
		case "multiply":
			result = num1 * num2;
			break;
		case "divide":
			if (num2 != 0) {
				result = num1 / num2;
			} else {
				result = 0;
			}
			break;
		default:
			result = 0;
			break;
		}
		return result;
	}

}