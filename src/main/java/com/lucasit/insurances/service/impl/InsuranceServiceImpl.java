package com.lucasit.insurances.service.impl;

import com.lucasit.insurances.exception.InsuranceExeption;
import com.lucasit.insurances.model.Car;
import com.lucasit.insurances.model.Customer;
import com.lucasit.insurances.model.Insurance;
import com.lucasit.insurances.repository.CarRepository;
import com.lucasit.insurances.repository.CustomerRepository;
import com.lucasit.insurances.repository.InsuranceRepository;
import com.lucasit.insurances.request.InsurancePostBody;
import com.lucasit.insurances.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {


    private final InsuranceRepository insuranceRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    @Override
    @Transactional
    public Insurance save(InsurancePostBody insurancePostBody) {

        Customer customer = customerRepository.findById(insurancePostBody.getCustomerId())
                .orElseThrow(() -> new InsuranceExeption(" Customer not found"));

        Car car = carRepository.findById(insurancePostBody.getCarId())
                .orElseThrow(() -> new InsuranceExeption(" Car not found"));

        Insurance insurance = Insurance.builder()
                .customer(customer)
                .car(car)
                .isActive(insurancePostBody.isActive())
                .creation(LocalDate.now())
                .updated(LocalDateTime.now())
                .build();

        return insuranceRepository.save(insurance);
    }


}
