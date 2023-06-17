package com.kodlamaio.inventoryservice.business.abstracts;

import com.kodlamaio.inventoryservice.business.dto.requests.creates.CreateModelRequest;
import com.kodlamaio.inventoryservice.business.dto.requests.updates.UpdateModelRequest;
import com.kodlamaio.inventoryservice.business.dto.responses.creates.CreateModelResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.gets.model.GetAllModelsResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.gets.model.GetModelResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.updates.UpdateModelResponse;

import java.util.List;
import java.util.UUID;

public interface ModelService {
    List<GetAllModelsResponse> getAll();
    GetModelResponse getById(UUID id);
    CreateModelResponse add(CreateModelRequest request);
    UpdateModelResponse update(UUID id, UpdateModelRequest request);
    void delete(UUID id);
}
