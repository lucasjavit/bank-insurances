package com.lucasit.insurances.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "claim")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonManagedReference
    private Car car;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    @JsonManagedReference
    private Driver driver;
}
