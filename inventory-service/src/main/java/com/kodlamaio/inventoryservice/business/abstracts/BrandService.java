package com.kodlamaio.inventoryservice.business.abstracts;

import com.kodlamaio.inventoryservice.business.dto.requests.creates.CreateBrandRequest;
import com.kodlamaio.inventoryservice.business.dto.requests.updates.UpdateBrandRequest;
import com.kodlamaio.inventoryservice.business.dto.responses.creates.CreateBrandResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.gets.brand.GetAllBrandsResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.gets.brand.GetBrandResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.updates.UpdateBrandResponse;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    GetBrandResponse getById(UUID id);
    CreateBrandResponse add(CreateBrandRequest request);
    UpdateBrandResponse update(UUID id, UpdateBrandRequest request);
    void delete(UUID id);
}
