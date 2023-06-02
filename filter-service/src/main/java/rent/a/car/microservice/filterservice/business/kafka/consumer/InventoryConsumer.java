package rent.a.car.microservice.filterservice.business.kafka.consumer;

import com.kodlamaio.commonpackage.events.invertory.BrandDeletedEvent;
import com.kodlamaio.commonpackage.events.invertory.CarCreatedEvent;
import com.kodlamaio.commonpackage.events.invertory.CarDeletedEvent;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import rent.a.car.microservice.filterservice.business.abstracts.FilterService;
import rent.a.car.microservice.filterservice.entities.Filter;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryConsumer {
    private final FilterService filterService;
    private final ModelMapperService mapperService;

    @KafkaListener(
            topics  = "car-created",
            groupId = "car-create"
    )
    public void consume(CarCreatedEvent event) {
        var filter = mapperService.forRequest().map(event, Filter.class);
        filterService.add(filter);
        log.info("Car created event consumed {}", event);
    }

    @KafkaListener(
            topics  = "car-deleted",
            groupId = "car-delete"
    )
    public void consume(CarDeletedEvent event) {
        filterService.deleteByCarId(event.getCarId());
        log.info("Car deleted event consumed {}", event);
    }

    @KafkaListener(
            topics  = "brand-deleted",
            groupId = "brand-delete"
    )
    public void consume(BrandDeletedEvent event) {
        filterService.deleteAllByBrandId(event.getBrandId());
        log.info("Brand deleted event consumed {}", event);
    }
}