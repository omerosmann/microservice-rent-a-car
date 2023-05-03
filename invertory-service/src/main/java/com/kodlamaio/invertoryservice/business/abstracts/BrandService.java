package com.kodlamaio.invertoryservice.business.abstracts;

import com.kodlamaio.invertoryservice.business.dto.requests.create.CreateBrandRequest;
import com.kodlamaio.invertoryservice.business.dto.requests.update.UpdateBrandRequest;
import com.kodlamaio.invertoryservice.business.dto.responses.create.CreateBrandResponse;
import com.kodlamaio.invertoryservice.business.dto.responses.get.GetAllBrandsResponse;
import com.kodlamaio.invertoryservice.business.dto.responses.get.GetBrandResponse;
import com.kodlamaio.invertoryservice.business.dto.responses.update.UpdateBrandResponse;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();

    GetBrandResponse getById(UUID id);

    CreateBrandResponse add(CreateBrandRequest request);

    UpdateBrandResponse update(UUID id, UpdateBrandRequest request);

    void delete(UUID id);
}
