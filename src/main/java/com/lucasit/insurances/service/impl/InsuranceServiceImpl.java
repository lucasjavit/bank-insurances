package com.lucasit.insurances.service.impl;

import com.lucasit.insurances.exception.InsuranceExeption;
import com.lucasit.insurances.mapper.CarMapper;
import com.lucasit.insurances.model.Car;
import com.lucasit.insurances.model.Customer;
import com.lucasit.insurances.model.Driver;
import com.lucasit.insurances.model.Insurance;
import com.lucasit.insurances.repository.CarRepository;
import com.lucasit.insurances.repository.CustomerRepository;
import com.lucasit.insurances.repository.InsuranceRepository;
import com.lucasit.insurances.request.InsuranceDriverResponse;
import com.lucasit.insurances.request.InsurancePatchBody;
import com.lucasit.insurances.request.InsurancePostBody;
import com.lucasit.insurances.request.InsuranceResponse;
import com.lucasit.insurances.rules.CarClaimRule;
import com.lucasit.insurances.rules.CarRule;
import com.lucasit.insurances.rules.DriverRule;
import com.lucasit.insurances.service.CarDriverService;
import com.lucasit.insurances.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    private final Integer SIX_PERCENT = 6;
    private final Integer TWO_PERCENT = 2;
    private final Integer ONE_HUNDRED = 100;

    private final InsuranceRepository insuranceRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final CarDriverService carDriverService;

    @Override
    @Transactional
    public Insurance save(InsurancePostBody insurancePostBody) {

        if (insurancePostBody == null) {
            throw new InsuranceExeption("The insurancePostBody cannot be null");
        }

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

    @Override
    @Transactional
    public Insurance update(Long insuranceId, InsurancePatchBody insurancePatchBody) {

        if (insuranceId == null || insurancePatchBody == null) {
            throw new InsuranceExeption("Elementes of request cannot be null");
        }

        Insurance insurance = findInsuranceById(insuranceId);

        validateInsurance(insurance);

        insurance.setActive(insurancePatchBody.isActive());

        if (insurancePatchBody.getCarId() != null) {
            Car car = carRepository.findById(insurancePatchBody.getCarId())
                    .orElseThrow(() -> new InsuranceExeption("Car not found"));

            insurance.setCar(car);
        }

        if (insurancePatchBody.getCustomerId() != null) {
            Customer customer = customerRepository.findById(insurancePatchBody.getCustomerId())
                    .orElseThrow(() -> new InsuranceExeption("Customer not found"));

            insurance.setCustomer(customer);
        }

        insurance.setUpdated(LocalDateTime.now());

        return insuranceRepository.save(insurance);
    }

    @Override
    public void delete(Long insuranceId) {
        Insurance insurance = findInsuranceById(insuranceId);

        validateInsurance(insurance);

        insurance.setUpdated(LocalDateTime.now());
        insurance.setActive(false);

        insuranceRepository.save(insurance);
    }

    @Override
    public InsuranceResponse getInsurance(Long insuranceId) {

        Insurance insurance = findInsuranceById(insuranceId);

        validateInsurance(insurance);

        InsuranceResponse insuranceResponse = new InsuranceResponse();

        insuranceResponse.setCustomerName(insurance.getCustomer().getName());
        insuranceResponse.setPercentValue(SIX_PERCENT);

        List<Driver> drivers = insurance.getCustomer().getDrivers();
        if (drivers != null) {

            List<InsuranceDriverResponse> driverResponses = new ArrayList<>();

            drivers
                    .forEach(driver -> {

                        InsuranceDriverResponse driverResponse = new InsuranceDriverResponse();

                        driverResponse.setDocument(driver.getDocument());
                        driverResponse.setAge(driver.calculateAge());

                        DriverRule driverRule = driver.getRule(carDriverService.isMainDriver(driver));

                        driverRule.validateDriver(insuranceResponse, driver, driverResponse);

                        driverResponses.add(driverResponse);
                    });

            insuranceResponse.setDrivers(driverResponses);
        }

        Car car = insurance.getCar();

        CarRule carRule = new CarClaimRule();
        carRule.setRule(insuranceResponse, car, TWO_PERCENT);

        insuranceResponse.setCar(CarMapper.INSTANCE.toDTO(car));

        var insuranceValue = calculateTotalInsurance(insuranceResponse.getPercentValue(), car.getFipeValue());

        insuranceResponse.setValueInsurance(insuranceValue.setScale(2));
        insuranceResponse.setActive(insurance.isActive());
        return insuranceResponse;
    }

    private BigDecimal calculateTotalInsurance(Integer percent, BigDecimal value) {
        if (percent == null && value == null) {
            throw new InsuranceExeption("Percent and value is null");
        }
        return value.multiply(BigDecimal.valueOf((double) percent / ONE_HUNDRED)).setScale(2);
    }

    private void validateInsurance(Insurance insurance) {
        if (insurance == null) {
            throw new InsuranceExeption("Insurance not found.");
        }

    }

    private Insurance findInsuranceById(Long insuranceId) {
        return insuranceRepository.findById(insuranceId)
                .orElseThrow(() -> new InsuranceExeption("Insurance not found"));
    }


}
