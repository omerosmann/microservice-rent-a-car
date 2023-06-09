package com.a.car.microservice.rentalservice.api.clients.car;

import com.kodlamaio.commonpackage.utils.dto.ClientResponse;
import com.kodlamaio.commonpackage.utils.dto.GetCarResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Slf4j
@Component
public class CarClientFallback implements CarClient {
    int i = 0;
    long lastCallTime = 0l;
    long timeDifference = 0l;

//  @Retry(name = "checkIfCarAvailable")  -->  Fault Tolerance Limiter Different Solution
//    @Retryable(maxAttempts = 5, backoff = @Backoff(value = 15000))
    @Override
    public ClientResponse checkIfCarAvailable(UUID carId) {
        timeDifference = System.currentTimeMillis() - lastCallTime;
        log.info(++i + ". INVENTORY SERVICE IS DOWN! : " + timeDifference);
        RestTemplate rt = new RestTemplate();
        lastCallTime = System.currentTimeMillis();
        throw new RuntimeException("INVENTORY-SERVICE NOT AVAILABLE RIGHT NOW!");
    }

    @Override
    public GetCarResponse getByIdForCar(UUID carId) {
        log.info("INVENTORY SERVICE IS DOWN!");
        throw new RuntimeException("INVENTORY_SERVICE NOT AVAILABLE RIGHT NOW!");
    }
}