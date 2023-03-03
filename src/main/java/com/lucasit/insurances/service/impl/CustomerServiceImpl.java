package com.lucasit.insurances.service.impl;

import com.lucasit.insurances.exception.InsuranceExeption;
import com.lucasit.insurances.mapper.CustomerMapper;
import com.lucasit.insurances.model.*;
import com.lucasit.insurances.repository.CarDriverRepository;
import com.lucasit.insurances.repository.CarRepository;
import com.lucasit.insurances.repository.CustomerRepository;
import com.lucasit.insurances.repository.DriverRepository;
import com.lucasit.insurances.request.CustomerPostBody;
import com.lucasit.insurances.service.ClaimService;
import com.lucasit.insurances.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final DriverRepository driverRepository;
    private final CarRepository carRepository;
    private final CarDriverRepository carDriverRepository;
    private final ClaimService claimService;


    @Transactional
    @Override
    public Customer save(CustomerPostBody customerPostBody) {
        Customer customer = customerRepository.save(CustomerMapper.INSTANCE.toEntity(customerPostBody));

        customerPostBody.getDrivers().forEach(elem -> {

            Car car = carRepository.findById(elem.getCarId())
                    .orElseThrow(() -> new InsuranceExeption("Car not founded."));

            Driver driver = driverRepository.save(Driver.builder()
                    .document(elem.getDocument())
                    .birthdate(elem.getBirthdate())
                    .customer(customer)
                    .build());

            CarDriver carDriver = CarDriver.builder()
                    .carDriverId(new CarDriverId())
                    .car(car)
                    .isMainDriver(elem.isMainDriver())
                    .driver(driver)
                    .build();

            car.setCarDrivers(new ArrayList<>());
            car.getCarDrivers().add(carDriver);

            driver.setCarDrivers(new ArrayList<>());
            driver.getCarDrivers().add(carDriver);

            carDriverRepository.save(carDriver);

            if (elem.isClaim()) {
                claimService.save(Claim.builder()
                        .driver(driver)
                        .car(car)
                        .build());
            }
        });

        return customer;

    }
}
