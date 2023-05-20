package com.kodlamaio.invertoryservice.business.kafka.consumer;

import com.kodlamaio.commonpackage.events.maintenance.MaintenanceCreatedEvent;
import com.kodlamaio.commonpackage.events.maintenance.MaintenanceDeletedEvent;
import com.kodlamaio.commonpackage.events.maintenance.MaintenanceReturnEvent;
import com.kodlamaio.invertoryservice.business.abstracts.CarService;
import com.kodlamaio.invertoryservice.entities.enums.State;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MaintenanceConsumer {
    private final CarService service;

    @KafkaListener(
            topics = "maintenance-created",
            groupId = "inventory-maintenance-create"
    )
    public void consume(MaintenanceCreatedEvent event) {
        service.changeStateByCarId(State.Maintenance, event.getCarId());
        log.info("Maintenance created event consumed {}", event);
    }

    @KafkaListener(
            topics = "maintenance-deleted",
            groupId = "inventory-maintenance-delete"
    )
    public void consume(MaintenanceDeletedEvent event) {
        service.changeStateByCarId(State.Available, event.getCarId());
        log.info("Maintenance deleted event consumed {}", event);
    }

    @KafkaListener(
            topics = "maintenance-returned",
            groupId = "inventory-maintenance-return"
    )
    public void consume(MaintenanceReturnEvent event) {
        service.changeStateByCarId(State.Available, event.getCarId());
        log.info("Maintenance returned event consumed {}", event);
    }
}
