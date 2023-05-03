package com.kodlamaio.invertoryservice.repository;

import com.kodlamaio.invertoryservice.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BrandRepository extends JpaRepository<Brand, UUID> {
}
