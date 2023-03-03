package com.lucasit.insurances.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lucasit.insurances.model.Car;
import com.lucasit.insurances.repository.CarRepository;
import com.lucasit.insurances.request.CarPostBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CarServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CarServiceImplTest {
    @MockBean
    private CarRepository carRepository;

    @Autowired
    private CarServiceImpl carServiceImpl;

    @Test
    void testSave() {
        Car car = new Car();
        when(carRepository.save((Car) any())).thenReturn(car);
        assertSame(car, carServiceImpl.save(new CarPostBody()));
        verify(carRepository).save((Car) any());
    }
}

