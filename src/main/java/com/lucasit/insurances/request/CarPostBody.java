package com.lucasit.insurances.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarPostBody {

    @NotNull(message = "The model cannot be empty")
    private String model;

    @NotNull(message = "The manufacturer cannot be empty")
    private String manufacturer;

    @JsonProperty("year")
    @NotNull(message = "The year cannot be empty")
    private String yearCar;

    @JsonProperty("fipe_value")
    @NotNull(message = "The fipe_value cannot be empty")
    private BigDecimal fipeValue;
}
