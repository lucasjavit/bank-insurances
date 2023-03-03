package com.lucasit.insurances.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDriver {

    @EmbeddedId
    private CarDriverId carDriverId;

    @ManyToOne
    @MapsId("carId")
    @JsonBackReference
    private Car car;

    @ManyToOne
    @MapsId("driverId")
    @JsonBackReference
    private Driver driver;

    private boolean isMainDriver;
}
