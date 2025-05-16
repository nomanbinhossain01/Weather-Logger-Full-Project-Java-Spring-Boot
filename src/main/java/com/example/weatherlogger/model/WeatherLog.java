package com.example.weatherlogger.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class WeatherLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private double temperature;
    private LocalDate date;
    private String condition;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
}