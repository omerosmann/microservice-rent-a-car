package com.kodlamaio.inventoryservice.business.rules;

import com.kodlamaio.commonpackage.utils.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.kodlamaio.inventoryservice.repository.ModelRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    private final ModelRepository repository;

    public void checkIfModelExists(UUID id)
    { if (!repository.existsById(id)) throw new BusinessException("MODEL_NOT_EXISTS"); }
}