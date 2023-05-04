package com.example.demo;

import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomNumberController {

	@GetMapping("/random")
	public RandomNumber randomNumber(@RequestParam(value = "number", defaultValue = "10") int number) {
		Random rand = new Random();
        int rn = rand.nextInt(number);
		
		return new RandomNumber(rn);
	}
}
