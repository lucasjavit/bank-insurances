package com.lucasit.insurances.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceResponse {

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("percent_value")
    private Integer percentValue;

    @JsonProperty("value_insurance")
    private BigDecimal valueInsurance;

    @JsonProperty("is_active")
    private boolean isActive;

    private List<InsuranceDriverResponse> drivers;

    private CarResponse car;

}
