package com.kodlamaio.inventoryservice.business.dto.responses.get.car;

import com.kodlamaio.inventoryservice.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCarResponse {
    private UUID id;
    private String brandName;
    private String modelName;
    private UUID modelId;
    private int modelYear;
    private String plate;
    private State state;
    private double dailyPrice;
}
