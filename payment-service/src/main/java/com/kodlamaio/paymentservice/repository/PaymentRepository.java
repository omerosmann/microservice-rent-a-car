package com.kodlamaio.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kodlamaio.paymentservice.entities.Payment;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    Payment findByCardNumber(String cardNumber);
    boolean existsByCardNumber(String cardNumber);
    boolean existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
            String cardNumber, String cardHolder, int cardExpirationYear, int cardExpirationMonth, String cardCvv
    );
}