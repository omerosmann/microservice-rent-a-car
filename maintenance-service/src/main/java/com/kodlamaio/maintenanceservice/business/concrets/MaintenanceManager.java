package com.kodlamaio.maintenanceservice.business.concrets;

import com.kodlamaio.commonpackage.events.maintenance.MaintenanceCreatedEvent;
import com.kodlamaio.commonpackage.events.maintenance.MaintenanceDeletedEvent;
import com.kodlamaio.commonpackage.events.maintenance.MaintenanceReturnEvent;
import com.kodlamaio.commonpackage.kafka.producer.KafkaProducer;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.maintenanceservice.business.abstracts.MaintenanceService;
import com.kodlamaio.maintenanceservice.business.dto.requests.CreateMaintenanceRequest;
import com.kodlamaio.maintenanceservice.business.dto.requests.UpdateMaintenanceRequest;
import com.kodlamaio.maintenanceservice.business.dto.responses.CreateMaintenanceResponse;
import com.kodlamaio.maintenanceservice.business.dto.responses.GetAllMaintenancesResponse;
import com.kodlamaio.maintenanceservice.business.dto.responses.GetMaintenanceResponse;
import com.kodlamaio.maintenanceservice.business.dto.responses.UpdateMaintenanceResponse;
import com.kodlamaio.maintenanceservice.business.rules.MaintenanceBusinessRules;
import com.kodlamaio.maintenanceservice.entities.Maintenance;
import com.kodlamaio.maintenanceservice.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MaintenanceManager implements MaintenanceService {

    private final MaintenanceRepository repository;
    private final ModelMapperService modelMapperService;
    private final KafkaProducer producer;
    private final MaintenanceBusinessRules rules;


    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> maintenances = repository.findAll();
        List<GetAllMaintenancesResponse> response = maintenances.stream()
                .map(maintenance -> modelMapperService.forResponse().map(maintenances, GetAllMaintenancesResponse.class)).toList();

        return response;
    }

    @Override
    public GetMaintenanceResponse getById(UUID id) {
        rules.checkIfMaintenanceExists(id);
        Maintenance maintenance = repository.findById(id).orElseThrow();
        GetMaintenanceResponse response = modelMapperService.forResponse().map(maintenance, GetMaintenanceResponse.class);

        return response;
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(UUID carId) {
        rules.checkIfCarIsNotUnderMaintenance(carId);
        Maintenance maintenance = repository.findMaintenanceByCarIdAndIsCompletedFalse(carId);
        maintenance.setCompleted(true);
        maintenance.setEndDate(LocalDateTime.now());
        repository.save(maintenance);
        sendKafkaMaintenanceReturnEvent(carId); //TODO : maintenance.getCarId;
        GetMaintenanceResponse response = modelMapperService.forResponse().map(maintenance, GetMaintenanceResponse.class);

        return response;
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        rules.checkIfCarAvailable(request.getCarId());
        rules.checkIfCarUnderMaintenance(request.getCarId());
        Maintenance maintenance = modelMapperService.forRequest().map(request, Maintenance.class);
        maintenance.setId(UUID.randomUUID());
        maintenance.setCompleted(false);
        maintenance.setStartDate(LocalDateTime.now());
        maintenance.setEndDate(null);
        repository.save(maintenance);
        sendKafkaMaintenanceCreatedEvent(maintenance.getCarId());

        CreateMaintenanceResponse response = modelMapperService.forResponse().map(maintenance, CreateMaintenanceResponse.class);

        return response;
    }

    @Override
    public UpdateMaintenanceResponse update(UUID id, UpdateMaintenanceRequest request) {
        rules.checkIfMaintenanceExists(id);
        Maintenance maintenance = modelMapperService.forRequest().map(request, Maintenance.class);
        maintenance.setId(id);
        repository.save(maintenance);
        UpdateMaintenanceResponse response = modelMapperService.forResponse().map(maintenance, UpdateMaintenanceResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfMaintenanceExists(id);
        sendKafkaMaintenanceDeletedEvent(id);
        repository.deleteById(id);

    }

    private void sendKafkaMaintenanceCreatedEvent(UUID carId) {
        producer.sendMessage(new MaintenanceCreatedEvent(carId), "maintenance-created");
    }

    private void sendKafkaMaintenanceDeletedEvent(UUID id) {
        var carId = repository.findById(id).orElseThrow().getCarId();
        producer.sendMessage(new MaintenanceDeletedEvent(carId), "maintenance-deleted");
    }

    private void sendKafkaMaintenanceReturnEvent(UUID id) {
        var carId = repository.findById(id).orElseThrow().getCarId();
        producer.sendMessage(new MaintenanceReturnEvent(carId), "maintenance-returned");
    }

}