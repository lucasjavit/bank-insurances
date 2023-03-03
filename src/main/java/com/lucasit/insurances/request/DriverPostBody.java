package com.lucasit.insurances.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverPostBody {

    @NotNull(message = "The document cannot be empty")
    private String document;

    @NotNull(message = "The birthdate cannot be empty")
    private LocalDate birthdate;

    @NotNull(message = "The car_id cannot be empty")
    @JsonProperty("car_id")
    private Long carId;

    @NotNull(message = "The is_main_driver cannot be empty")
    @JsonProperty("is_main_driver")
    private boolean isMainDriver;

    @NotNull(message = "The is_claim cannot be empty")
    @JsonProperty("is_claim")
    private boolean isClaim;

}
