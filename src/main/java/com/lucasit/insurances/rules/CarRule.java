package com.lucasit.insurances.rules;

import com.lucasit.insurances.model.Car;
import com.lucasit.insurances.request.InsuranceResponse;

public interface CarRule {
    void setRule(InsuranceResponse insuranceResponse, Car car, Integer percent);
}
