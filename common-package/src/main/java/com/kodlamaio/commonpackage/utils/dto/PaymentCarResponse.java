package com.kodlamaio.commonpackage.utils.dto;

import com.kodlamaio.commonpackage.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCarResponse implements Event {
    private UUID carId;
    private String brandName;
    private String modelName;
    private int modelYear;
    private String plate;
    private String cardHolder;
    private double dailyPrice;
    private double totalPrice;
    private int rentedForDays;
    private LocalDate rentedAt;
}
