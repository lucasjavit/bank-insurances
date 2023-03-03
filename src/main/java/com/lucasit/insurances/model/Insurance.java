package com.lucasit.insurances.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "insurance")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_id")
    private Long id;

    @NotNull(message = "creation_dt cannot be null")
    @Column(name = "creation_dt")
    private LocalDate creation;

    @NotNull(message = "updated_at cannot be null")
    @Column(name = "updated_at")
    private LocalDateTime updated;

    @NotNull(message = "is_active cannot be null")
    @Column(name = "is_active")
    private boolean isActive;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonManagedReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonManagedReference
    private Car car;

}
