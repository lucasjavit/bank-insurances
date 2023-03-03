package com.lucasit.insurances.service;

import com.lucasit.insurances.model.Customer;
import com.lucasit.insurances.request.CustomerPostBody;

public interface CustomerService {

    Customer save(CustomerPostBody customerPostBody);
}
