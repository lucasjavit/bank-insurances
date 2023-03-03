package com.lucasit.insurances.service.impl;

import com.lucasit.insurances.exception.InsuranceExeption;
import com.lucasit.insurances.model.Customer;
import com.lucasit.insurances.repository.CarDriverRepository;
import com.lucasit.insurances.repository.CarRepository;
import com.lucasit.insurances.repository.CustomerRepository;
import com.lucasit.insurances.repository.DriverRepository;
import com.lucasit.insurances.request.CustomerPostBody;
import com.lucasit.insurances.service.ClaimService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CustomerServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {
    @MockBean
    private CarDriverRepository carDriverRepository;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private ClaimService claimService;

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @MockBean
    private DriverRepository driverRepository;

    @Test
    void testSave() {
        Customer customer = new Customer();
        when(customerRepository.save((Customer) any())).thenReturn(customer);
        assertSame(customer, customerServiceImpl.save(new CustomerPostBody()));
        verify(customerRepository).save((Customer) any());
    }

    @Test
    void testSave2() {
        when(customerRepository.save((Customer) any())).thenReturn(null);
        assertThrows(InsuranceExeption.class, () -> customerServiceImpl.save(new CustomerPostBody()));
        verify(customerRepository).save((Customer) any());
    }

    @Test
    void testSave3() {
        Customer customer = new Customer();
        when(customerRepository.save((Customer) any())).thenReturn(customer);

        CustomerPostBody customerPostBody = new CustomerPostBody();
        customerPostBody.setDrivers(new ArrayList<>());
        assertSame(customer, customerServiceImpl.save(customerPostBody));
        verify(customerRepository).save((Customer) any());
    }

    @Test
    void testSave4() {
        when(customerRepository.save((Customer) any())).thenReturn(new Customer());
        assertThrows(InsuranceExeption.class, () -> customerServiceImpl.save(null));
    }

}

