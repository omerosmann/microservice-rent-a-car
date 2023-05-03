package com.kodlamaio.invertoryservice.repository;

import com.kodlamaio.invertoryservice.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModelRepository extends JpaRepository<Model, UUID> {
}
