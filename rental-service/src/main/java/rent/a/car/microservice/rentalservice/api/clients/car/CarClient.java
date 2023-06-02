package rent.a.car.microservice.rentalservice.api.clients.car;

import com.kodlamaio.commonpackage.utils.dto.ClientResponse;
import com.kodlamaio.commonpackage.utils.dto.GetCarResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "inventory-service", fallback = CarClientFallback.class)
public interface CarClient {

    @Retry(name = "checkIfCarAvailable")   // -->  Fault Tolerance Limiter Different Solution
    @GetMapping(value = "/api/cars/check-car-available/{carId}")
    ClientResponse checkIfCarAvailable(@PathVariable UUID carId);

    @GetMapping(value = "/api/cars/{carId}")
    GetCarResponse getByIdForCar(@PathVariable UUID carId);
}