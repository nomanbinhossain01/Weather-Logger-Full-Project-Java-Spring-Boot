package com.example.weatherlogger.repository;

import com.example.weatherlogger.model.WeatherLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface WeatherLogRepository extends JpaRepository<WeatherLog, Long> {
    List<WeatherLog> findByCityContainingIgnoreCase(String city);
    List<WeatherLog> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT w.city, MAX(w.temperature), MIN(w.temperature), AVG(w.temperature) FROM WeatherLog w GROUP BY w.city")
    List<Object[]> findWeatherSummaries();
}