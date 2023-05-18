package com.kodlamaio.rentalservice.api.clients;

import com.kodlamaio.commonpackage.utils.dto.ClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


@Slf4j
@Component
public class CarClientFallback implements CarClient {

    int i = 0;
    long lastCallTime = 0l;
    long timeDifference = 0l;

    @Retryable(maxAttempts = 5, backoff = @Backoff(value = 15000))
    //@Retry(name = "checkIfCarAvailable")  !!! Fault Tolerance Limiter Different Solution !!!
    @Override
    public ClientResponse checkIfCarAvailable(UUID carId) {
        timeDifference = System.currentTimeMillis() - lastCallTime;
        log.info(++i + ". INVENTORY SERVICE IS DOWN! : " + timeDifference);
        RestTemplate rt = new RestTemplate();
        lastCallTime = System.currentTimeMillis();
        throw new RuntimeException("INVENTORY-SERVICE NOT AVAILABLE RIGHT NOW!");
    }


}
