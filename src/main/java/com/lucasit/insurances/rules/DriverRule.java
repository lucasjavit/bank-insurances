package com.lucasit.insurances.rules;

import com.lucasit.insurances.model.Driver;
import com.lucasit.insurances.request.InsuranceDriverResponse;
import com.lucasit.insurances.request.InsuranceResponse;

public interface DriverRule {

    void validateDriver(InsuranceResponse insuranceResponse, Driver driver, InsuranceDriverResponse driverResponse);

}
