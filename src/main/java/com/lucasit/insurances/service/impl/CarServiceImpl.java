package com.lucasit.insurances.service.impl;

import com.lucasit.insurances.mapper.CarMapper;
import com.lucasit.insurances.model.Car;
import com.lucasit.insurances.repository.CarRepository;
import com.lucasit.insurances.request.CarPostBody;
import com.lucasit.insurances.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {


    private final CarRepository carRepository;

    @Override
    public Car save(CarPostBody carPostBody) {
        Car car = CarMapper.INSTANCE.toEntity(carPostBody);
        return carRepository.save(car);
    }

}
