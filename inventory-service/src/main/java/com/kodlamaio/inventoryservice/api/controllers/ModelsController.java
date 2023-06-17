package com.kodlamaio.inventoryservice.api.controllers;

import com.kodlamaio.inventoryservice.business.dto.responses.creates.CreateModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.kodlamaio.inventoryservice.business.abstracts.ModelService;
import com.kodlamaio.inventoryservice.business.dto.requests.creates.CreateModelRequest;
import com.kodlamaio.inventoryservice.business.dto.requests.updates.UpdateModelRequest;
import com.kodlamaio.inventoryservice.business.dto.responses.gets.model.GetAllModelsResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.gets.model.GetModelResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.updates.UpdateModelResponse;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/models")
public class ModelsController {
    private final ModelService service;

    @GetMapping
    public List<GetAllModelsResponse> getAll()
    { return service.getAll(); }

    @GetMapping("/{id}")
    public GetModelResponse getById(@PathVariable UUID id)
    { return service.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateModelResponse add(@Valid @RequestBody CreateModelRequest request)
    { return service.add(request); }

    @PutMapping("/{id}")
    public UpdateModelResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateModelRequest request)
    { return service.update(id, request); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id)
    { service.delete(id); }
}