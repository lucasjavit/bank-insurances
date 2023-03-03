package com.lucasit.insurances.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceDriverResponse {

    private String document;

    private Integer age;

    @JsonProperty("is_main_driver")
    private boolean isMainDriver;

    @JsonProperty("is_claim")
    private boolean isClaim;

}
