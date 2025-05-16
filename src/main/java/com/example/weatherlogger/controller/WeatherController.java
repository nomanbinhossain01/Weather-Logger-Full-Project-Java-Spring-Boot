package com.example.weatherlogger.controller;

import com.example.weatherlogger.model.WeatherLog;
import com.example.weatherlogger.repository.WeatherLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class WeatherController {

    @Autowired
    private WeatherLogRepository repository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("logs", repository.findAll());
        model.addAttribute("log", new WeatherLog());
        model.addAttribute("summary", repository.findWeatherSummaries());
        return "index";
    }

    @PostMapping("/add")
    public String addLog(@ModelAttribute WeatherLog log) {
        repository.save(log);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/filter")
    public String filter(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model) {
        List<WeatherLog> results = repository.findAll();
        if (city != null && !city.isEmpty()) {
            results = repository.findByCityContainingIgnoreCase(city);
        }
        if (startDate != null && endDate != null) {
            results = repository.findByDateBetween(startDate, endDate);
        }
        model.addAttribute("logs", results);
        model.addAttribute("log", new WeatherLog());
        model.addAttribute("summary", repository.findWeatherSummaries());
        return "index";
    }
}