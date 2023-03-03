package com.lucasit.insurances.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
    private String model;
    private String manufacturer;
    private String yearCar;
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
