package com.lucasit.insurances.repository;

import com.lucasit.insurances.model.CarDriver;
import com.lucasit.insurances.model.CarDriverId;
import com.lucasit.insurances.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarDriverRepository extends JpaRepository<CarDriver, CarDriverId> {

    Optional<CarDriver> findCarDriverByDriverAndIsMainDriver(Driver driver, boolean isMainDriver);
}
