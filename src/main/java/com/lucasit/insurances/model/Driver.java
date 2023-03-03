package com.lucasit.insurances.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lucasit.insurances.exception.InsuranceExeption;
import com.lucasit.insurances.rules.CommonDriverRule;
import com.lucasit.insurances.rules.DriverRule;
import com.lucasit.insurances.rules.MainDriverRule;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Long id;

    @Column(unique = true)
    @NotNull(message = "document cannot be null")
    private String document;
    @NotNull(message = "birthdate cannot be null")
    private LocalDate birthdate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonManagedReference
    private Customer customer;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<CarDriver> carDrivers;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Claim> claims;

    public Integer calculateAge() {
        if (this.birthdate == null) {
            throw new InsuranceExeption("The birthdate is empty");
        }
        return (LocalDate.now().getYear() - this.birthdate.getYear());
    }

    public DriverRule getRule(boolean isMainDriver) {
        if (isMainDriver) {
            return new MainDriverRule();
        } else {
            return new CommonDriverRule();
        }
    }
}
