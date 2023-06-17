package com.a.car.microservice.rentalservice.business.rules;

import com.kodlamaio.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.kodlamaio.commonpackage.utils.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.a.car.microservice.rentalservice.api.clients.payment.PaymentClient;
import com.a.car.microservice.rentalservice.repository.RentalRepository;
import com.a.car.microservice.rentalservice.api.clients.car.CarClient;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository repository;
    private final CarClient carClient;
    private final PaymentClient paymentClient;

    public void checkIfRentalExists(UUID id)
    { if (!repository.existsById(id)) throw new BusinessException("RENTAL_NOT_EXISTS"); }

    public void ensureCarIsAvailable(UUID carId) {
        var response = carClient.checkIfCarAvailable(carId);

        if (!response.isSuccess()) throw new BusinessException(response.getMessage());
    }

    public void ensurePaymentIsValid(CreateRentalPaymentRequest request){
        var response= paymentClient.paymentValidation(request);

        if (!response.isSuccess()) throw new BusinessException(response.getMessage());
    }
}