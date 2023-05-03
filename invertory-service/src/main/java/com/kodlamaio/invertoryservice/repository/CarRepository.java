package com.kodlamaio.invertoryservice.repository;

import com.kodlamaio.invertoryservice.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
}
