package com.lucasit.insurances.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePatchBody {


    @NotNull(message = "The customer_id cannot be empty")
    @JsonProperty("customer_id")
    private Long customerId;

    @NotNull(message = "The car_id cannot be empty")
    @JsonProperty("car_id")
    private Long carId;

    @NotNull(message = "The is_active cannot be empty")
    @JsonProperty("is_active")
    private boolean isActive;
}