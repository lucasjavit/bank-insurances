package com.lucasit.insurances.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPostBody {

    @NotNull(message = "The name cannot be empty")
    private String name;

    @NotNull(message = "The drivers cannot be empty")
    private List<DriverPostBody> drivers;
}
