package com.lucasit.insurances.rules;

import com.lucasit.insurances.exception.InsuranceExeption;
import com.lucasit.insurances.model.Driver;
import com.lucasit.insurances.request.InsuranceDriverResponse;
import com.lucasit.insurances.request.InsuranceResponse;

public class CommonDriverRule implements DriverRule {

    private final Integer TWO_PERCENT = 2;


    @Override
    public void validateDriver(InsuranceResponse insuranceResponse,
                               Driver driver, InsuranceDriverResponse driverResponse) {
        if (driver.getClaims() != null) {
            addValue(insuranceResponse, TWO_PERCENT);
            driverResponse.setClaim(true);
        }
    }

    private void addValue(InsuranceResponse insuranceResponse, Integer percent) {
        if (percent == null) {
            throw new InsuranceExeption("Percent is null");
        }
        var value = insuranceResponse.getPercentValue() + percent;
        insuranceResponse.setPercentValue(value);
    }
}
