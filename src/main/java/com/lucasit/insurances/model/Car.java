package com.lucasit.insurances.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "car")
@RequiredArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;
    @NotNull(message = "model cannot be null")
    private String model;
    @NotNull(message = "model cannot be null")
    private String manufacturer;
    @NotNull(message = "year cannot be null")
    private String yearCar;
    @NotNull(message = "model fipe_value be null")
    private BigDecimal fipeValue;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<CarDriver> carDrivers;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Claim> claims;

    @OneToMany(mappedBy = "car")
    @JsonBackReference
    private List<Insurance> insurances;

}
