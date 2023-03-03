package com.lucasit.insurances.service.impl;

import com.lucasit.insurances.exception.InsuranceExeption;
import com.lucasit.insurances.model.Driver;
import com.lucasit.insurances.repository.CarDriverRepository;
import com.lucasit.insurances.service.CarDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarDriverServiceImpl implements CarDriverService {

    private final CarDriverRepository carDriverRepository;

    public boolean isMainDriver(Driver driver) {
        if (driver == null) {
            throw new InsuranceExeption("driver is null");
        }
        return carDriverRepository.findCarDriverByDriverAndIsMainDriver(driver, true).isPresent();
    }


}
