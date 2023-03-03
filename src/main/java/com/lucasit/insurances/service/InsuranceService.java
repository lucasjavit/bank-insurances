package com.lucasit.insurances.service;

import com.lucasit.insurances.model.Insurance;
import com.lucasit.insurances.request.InsurancePatchBody;
import com.lucasit.insurances.request.InsurancePostBody;

public interface InsuranceService {

    Insurance save(InsurancePostBody insurancePostBody);

    Insurance update(Long insuranceId, InsurancePatchBody insurancePatchBody);

    void delete(Long insuranceId);

}
