package com.lucasit.insurances.model;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CarDriverId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long carId;
    private Long driverId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDriverId that = (CarDriverId) o;
        return Objects.equals(carId, that.carId) && Objects.equals(driverId, that.driverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, driverId);
    }
}
