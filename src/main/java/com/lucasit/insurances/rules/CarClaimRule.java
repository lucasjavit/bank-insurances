package com.lucasit.insurances.rules;

import com.lucasit.insurances.exception.InsuranceExeption;
import com.lucasit.insurances.model.Car;
import com.lucasit.insurances.request.InsuranceResponse;

public class CarClaimRule implements CarRule {

    public void setRule(InsuranceResponse insuranceResponse, Car car, Integer percent) {
        validPercent(percent);
        validateCar(car);

        var value = insuranceResponse.getPercentValue() + percent;
        insuranceResponse.setPercentValue(value);
    }

    private void validateCar(Car car) {
        if (car == null) {
            throw new InsuranceExeption("Car is null");
        }
    }

    private void validPercent(Integer percent) {
        if (percent == null) {
            throw new InsuranceExeption("Percent is null");
        }
    }

}
