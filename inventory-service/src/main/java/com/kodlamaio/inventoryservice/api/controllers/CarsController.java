package com.kodlamaio.inventoryservice.api.controllers;

import com.kodlamaio.commonpackage.utils.constants.Roles;
import com.kodlamaio.commonpackage.utils.dto.ClientResponse;
import com.kodlamaio.commonpackage.utils.dto.GetCarResponse;
import com.kodlamaio.inventoryservice.business.dto.requests.updates.UpdateCarRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import com.kodlamaio.inventoryservice.business.abstracts.CarService;
import com.kodlamaio.inventoryservice.business.dto.requests.creates.CreateCarRequest;
import com.kodlamaio.inventoryservice.business.dto.responses.creates.CreateCarResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.gets.car.GetAllCarsResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.updates.UpdateCarResponse;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cars")
public class CarsController {
    private final CarService service;

    @GetMapping
    //Secured,PreAuthorize,PostAuthorize
    //@Secured("ROLE_admin")
    @PreAuthorize(Roles.AdminAndUser)
    public List<GetAllCarsResponse> getAll()
    { return service.getAll(); }

    @GetMapping("/{id}")
//    @PostAuthorize("hasRole('admin') || returnObject.modelYear == 2019")
    public GetCarResponse getById(@PathVariable UUID id, @AuthenticationPrincipal Jwt jwt)
    { return service.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCarResponse add(@Valid @RequestBody CreateCarRequest request)
    { return service.add(request); }

    @PutMapping("/{id}")
    public UpdateCarResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateCarRequest request)
    { return service.update(id, request); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id)
    { service.delete(id); }

    @GetMapping("/check-car-available/{id}")
    public ClientResponse checkIfCarAvailable(@PathVariable UUID id)
    { return service.checkIfCarAvailable(id); }
}
