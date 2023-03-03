package com.lucasit.insurances.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_iD")
    private Long id;

    @NotNull(message = "name cannot be null")
    private String name;

    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private List<Driver> drivers;

    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private List<Insurance> insurances;

}
