package com.lucasit.insurances.service.impl;

import com.lucasit.insurances.exception.InsuranceExeption;
import com.lucasit.insurances.model.CarDriver;
import com.lucasit.insurances.model.Driver;
import com.lucasit.insurances.repository.CarDriverRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CarDriverServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CarDriverServiceImplTest {

    @MockBean
    private CarDriverRepository carDriverRepository;

    @Autowired
    private CarDriverServiceImpl carDriverServiceImpl;

    @Test
    void testIsMainDriver() {
        when(carDriverRepository.findCarDriverByDriverAndIsMainDriver((Driver) any(), anyBoolean()))
                .thenReturn(Optional.of(new CarDriver()));
        assertTrue(carDriverServiceImpl.isMainDriver(new Driver()));
        verify(carDriverRepository).findCarDriverByDriverAndIsMainDriver((Driver) any(), anyBoolean());
    }

    @Test
    void testIsMainDriver3() {
        when(carDriverRepository.findCarDriverByDriverAndIsMainDriver((Driver) any(), anyBoolean()))
                .thenReturn(Optional.empty());
        assertFalse(carDriverServiceImpl.isMainDriver(new Driver()));
        verify(carDriverRepository).findCarDriverByDriverAndIsMainDriver((Driver) any(), anyBoolean());
    }


    @Test
    void testIsMainDriver4() {
        when(carDriverRepository.findCarDriverByDriverAndIsMainDriver((Driver) any(), anyBoolean()))
                .thenReturn(Optional.of(new CarDriver()));
        assertThrows(InsuranceExeption.class, () -> carDriverServiceImpl.isMainDriver(null));
    }


    @Test
    void testIsMainDriver5() {
        when(carDriverRepository.findCarDriverByDriverAndIsMainDriver((Driver) any(), anyBoolean()))
                .thenThrow(new InsuranceExeption("Not all who wander are lost"));
        assertThrows(InsuranceExeption.class, () -> carDriverServiceImpl.isMainDriver(new Driver()));
        verify(carDriverRepository).findCarDriverByDriverAndIsMainDriver((Driver) any(), anyBoolean());
    }
}

