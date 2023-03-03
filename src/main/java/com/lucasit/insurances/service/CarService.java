package com.lucasit.insurances.service;

import com.lucasit.insurances.model.Car;
import com.lucasit.insurances.request.CarPostBody;

public interface CarService {

    Car save(CarPostBody carPostBody);
}
