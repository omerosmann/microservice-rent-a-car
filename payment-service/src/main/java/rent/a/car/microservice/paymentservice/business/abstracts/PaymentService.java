package rent.a.car.microservice.paymentservice.business.abstracts;


import com.kodlamaio.commonpackage.utils.dto.ClientResponse;
import com.kodlamaio.commonpackage.utils.dto.CreateRentalPaymentRequest;
import rent.a.car.microservice.paymentservice.business.dto.requests.CreatePaymentRequest;
import rent.a.car.microservice.paymentservice.business.dto.requests.UpdatePaymentRequest;
import rent.a.car.microservice.paymentservice.business.dto.responses.CreatePaymentResponse;
import rent.a.car.microservice.paymentservice.business.dto.responses.GetAllPaymentsResponse;
import rent.a.car.microservice.paymentservice.business.dto.responses.GetPaymentResponse;
import rent.a.car.microservice.paymentservice.business.dto.responses.UpdatePaymentResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
        List<GetAllPaymentsResponse> getAll();
        GetPaymentResponse getById(UUID id);
        CreatePaymentResponse add(CreatePaymentRequest request);
        UpdatePaymentResponse update(UUID id, UpdatePaymentRequest request);
        void delete(UUID id);
        ClientResponse processRentalPayment(CreateRentalPaymentRequest request);
    }