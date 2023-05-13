package com.kodlamaio.invertoryservice.business.rules;

import com.kodlamaio.commonpackage.utils.exception.BusinessException;
import com.kodlamaio.invertoryservice.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    private final BrandRepository repository;

    public void checkIfBrandExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("BRAND_NOT_EXISTS");
        }
    }
}
