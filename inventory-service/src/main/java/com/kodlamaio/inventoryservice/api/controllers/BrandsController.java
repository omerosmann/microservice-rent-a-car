package com.kodlamaio.inventoryservice.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.kodlamaio.inventoryservice.business.abstracts.BrandService;
import com.kodlamaio.inventoryservice.business.dto.requests.creates.CreateBrandRequest;
import com.kodlamaio.inventoryservice.business.dto.requests.updates.UpdateBrandRequest;
import com.kodlamaio.inventoryservice.business.dto.responses.creates.CreateBrandResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.gets.brand.GetAllBrandsResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.gets.brand.GetBrandResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.updates.UpdateBrandResponse;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/brands")
public class BrandsController {
    private final BrandService service;

    @GetMapping
    public List<GetAllBrandsResponse> getAll()
    { return service.getAll(); }

    @GetMapping("/{id}")
    public GetBrandResponse getById(@PathVariable UUID id)
    { return service.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse add(@Valid @RequestBody CreateBrandRequest request)
    { return service.add(request); }

    @PutMapping("/{id}")
    public UpdateBrandResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateBrandRequest request)
    { return service.update(id, request); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id)
    { service.delete(id); }
}