package com.example.demo;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class RandomNumberController {

    @GetMapping("/")
    public String showForm() {
        return "randomNumberForm";
    }

    @PostMapping("/generate")
    public String generateNumbers(@RequestParam("minValue") int minValue, @RequestParam("maxValue") int maxValue, Model model) {
        List<Integer> numbers = generateRandomNumbers(minValue, maxValue, 1000);
        double average = calculateAverage(numbers);

        model.addAttribute("numbers", numbers);
        model.addAttribute("average", average);

        return "randomNumbers";
    }

    private List<Integer> generateRandomNumbers(int minValue, int maxValue, int count) {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int randomNumber = random.nextInt(maxValue - minValue + 1) + minValue;
            numbers.add(randomNumber);
        }

        return numbers;
    }

    private double calculateAverage(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return 0.0;
        }

        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        return (double) sum / numbers.size();
    }
}
