package com.kodlamaio.paymentservice.adapters;

import com.kodlamaio.paymentservice.business.abstracts.PosService;
import org.springframework.stereotype.Service;

@Service
public class FakePosServiceAdapter implements PosService {
    @Override
    public void pay() {
        boolean isPaymentSuccessful = true;
        if (!isPaymentSuccessful) throw new RuntimeException("PAYMENT_FAILED");
    }
}