package com.kodlamaio.invertoryservice.business.concretes;

import com.kodlamaio.commonpackage.events.CarCreatedEvent;
import com.kodlamaio.commonpackage.events.CarDeletedEvent;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.invertoryservice.business.abstracts.CarService;
import com.kodlamaio.invertoryservice.business.dto.requests.create.CreateCarRequest;
import com.kodlamaio.invertoryservice.business.dto.requests.update.UpdateCarRequest;
import com.kodlamaio.invertoryservice.business.dto.responses.create.CreateCarResponse;
import com.kodlamaio.invertoryservice.business.dto.responses.get.GetAllCarsResponse;
import com.kodlamaio.invertoryservice.business.dto.responses.get.GetCarResponse;
import com.kodlamaio.invertoryservice.business.dto.responses.update.UpdateCarResponse;
import com.kodlamaio.invertoryservice.business.kafka.producer.InventoryProducer;
import com.kodlamaio.invertoryservice.business.rules.CarBusinessRules;
import com.kodlamaio.invertoryservice.entities.Car;
import com.kodlamaio.invertoryservice.entities.enums.State;
import com.kodlamaio.invertoryservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository repository;
    private final ModelMapperService mapper;
    private final CarBusinessRules rules;
    private final InventoryProducer producer;

    @Override
    public List<GetAllCarsResponse> getAll() {
        var cars = repository.findAll();
        var response = cars
                .stream()
                .map(car -> mapper.forResponse().map(car, GetAllCarsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetCarResponse getById(UUID id) {
        rules.checkIfCarExists(id);
        var car = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(car, GetCarResponse.class);

        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        var car = mapper.forRequest().map(request, Car.class);
        car.setId(UUID.randomUUID());
        car.setState(State.Available);
        var createdCar = repository.save(car);
        sendKafkaCarCreatedEvent(createdCar);
        var response = mapper.forResponse().map(createdCar, CreateCarResponse.class);

        return response;
    }

    @Override
    public UpdateCarResponse update(UUID id, UpdateCarRequest request) {
        rules.checkIfCarExists(id);
        var car = mapper.forRequest().map(request, Car.class);
        car.setId(id);
        repository.save(car);
        var response = mapper.forResponse().map(car, UpdateCarResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfCarExists(id);
        repository.deleteById(id);
        sendKafkaCarDeletedEvent(id);
    }

    private void sendKafkaCarCreatedEvent(Car createdCar) {
        var event = mapper.forResponse().map(createdCar, CarCreatedEvent.class);
        producer.sendMessage(event);
    }

    private void sendKafkaCarDeletedEvent(UUID id) {
        producer.sendMessage(new CarDeletedEvent(id));
    }
}
