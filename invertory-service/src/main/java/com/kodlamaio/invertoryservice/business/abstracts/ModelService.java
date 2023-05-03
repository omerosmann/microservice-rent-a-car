package com.kodlamaio.invertoryservice.business.abstracts;

import com.kodlamaio.invertoryservice.business.dto.requests.create.CreateModelRequest;
import com.kodlamaio.invertoryservice.business.dto.requests.update.UpdateModelRequest;
import com.kodlamaio.invertoryservice.business.dto.responses.create.CreateModelResponse;
import com.kodlamaio.invertoryservice.business.dto.responses.get.GetAllModelsResponse;
import com.kodlamaio.invertoryservice.business.dto.responses.get.GetModelResponse;
import com.kodlamaio.invertoryservice.business.dto.responses.update.UpdateModelResponse;

import java.util.List;
import java.util.UUID;

public interface ModelService {
    List<GetAllModelsResponse> getAll();

    GetModelResponse getById(UUID id);

    CreateModelResponse add(CreateModelRequest request);

    UpdateModelResponse update(UUID id, UpdateModelRequest request);

    void delete(UUID id);
}
