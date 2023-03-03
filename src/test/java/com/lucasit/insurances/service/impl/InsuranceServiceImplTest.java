package com.lucasit.insurances.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lucasit.insurances.exception.InsuranceExeption;
import com.lucasit.insurances.model.Car;
import com.lucasit.insurances.model.Customer;
import com.lucasit.insurances.model.Driver;
import com.lucasit.insurances.model.Insurance;
import com.lucasit.insurances.repository.CarRepository;
import com.lucasit.insurances.repository.CustomerRepository;
import com.lucasit.insurances.repository.InsuranceRepository;
import com.lucasit.insurances.request.CarResponse;
import com.lucasit.insurances.request.InsurancePatchBody;
import com.lucasit.insurances.request.InsurancePostBody;
import com.lucasit.insurances.request.InsuranceResponse;
import com.lucasit.insurances.service.CarDriverService;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {InsuranceServiceImpl.class})
@ExtendWith(SpringExtension.class)
class InsuranceServiceImplTest {
    @MockBean
    private CarDriverService carDriverService;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private InsuranceRepository insuranceRepository;

    @Autowired
    private InsuranceServiceImpl insuranceServiceImpl;


    @Test
    void testSave() {
        Insurance insurance = new Insurance();
        when(insuranceRepository.save((Insurance) any())).thenReturn(insurance);
        when(customerRepository.findById((Long) any())).thenReturn(Optional.of(new Customer()));
        when(carRepository.findById((Long) any())).thenReturn(Optional.of(new Car()));
        assertSame(insurance, insuranceServiceImpl.save(new InsurancePostBody()));
        verify(insuranceRepository).save((Insurance) any());
        verify(customerRepository).findById((Long) any());
        verify(carRepository).findById((Long) any());
    }


    @Test
    void testSave2() {
        when(insuranceRepository.save((Insurance) any())).thenThrow(new InsuranceExeption("Not all who wander are lost"));
        when(customerRepository.findById((Long) any())).thenReturn(Optional.of(new Customer()));
        when(carRepository.findById((Long) any())).thenReturn(Optional.of(new Car()));
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.save(new InsurancePostBody()));
        verify(insuranceRepository).save((Insurance) any());
        verify(customerRepository).findById((Long) any());
        verify(carRepository).findById((Long) any());
    }


    @Test
    void testSave4() {
        when(insuranceRepository.save((Insurance) any())).thenReturn(new Insurance());
        when(customerRepository.findById((Long) any())).thenReturn(Optional.empty());
        when(carRepository.findById((Long) any())).thenReturn(Optional.of(new Car()));
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.save(new InsurancePostBody()));
        verify(customerRepository).findById((Long) any());
    }

   @Test
    void testSave6() {
        when(insuranceRepository.save((Insurance) any())).thenReturn(new Insurance());
        when(customerRepository.findById((Long) any())).thenReturn(Optional.of(new Customer()));
        when(carRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.save(new InsurancePostBody()));
        verify(customerRepository).findById((Long) any());
        verify(carRepository).findById((Long) any());
    }


    @Test
    void testSave7() {
        when(insuranceRepository.save((Insurance) any())).thenReturn(new Insurance());
        when(customerRepository.findById((Long) any())).thenReturn(Optional.of(new Customer()));
        when(carRepository.findById((Long) any())).thenReturn(Optional.of(new Car()));
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.save(null));
    }


    @Test
    void testSave8() {
        when(insuranceRepository.save((Insurance) any())).thenReturn(new Insurance());
        when(customerRepository.findById((Long) any())).thenReturn(Optional.of(new Customer()));
        when(carRepository.findById((Long) any())).thenReturn(Optional.of(new Car()));
        InsurancePostBody insurancePostBody = mock(InsurancePostBody.class);
        when(insurancePostBody.isActive()).thenThrow(new InsuranceExeption("Not all who wander are lost"));
        when(insurancePostBody.getCarId()).thenThrow(new InsuranceExeption("Not all who wander are lost"));
        when(insurancePostBody.getCustomerId()).thenThrow(new InsuranceExeption("Not all who wander are lost"));
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.save(insurancePostBody));
        verify(insurancePostBody).getCustomerId();
    }


    @Test
    void testUpdate() {
        when(insuranceRepository.findById((Long) any())).thenReturn(Optional.of(new Insurance()));
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.update(123L, new InsurancePatchBody()));
        verify(insuranceRepository).findById((Long) any());
    }


    @Test
    void testUpdate2() {
        LocalDate creation = LocalDate.ofEpochDay(1L);
        LocalDateTime updated = LocalDateTime.of(1, 1, 1, 1, 1);
        Customer customer = new Customer();
        when(insuranceRepository.findById((Long) any()))
                .thenReturn(Optional.of(new Insurance(123L, creation, updated, true, customer, new Car())));
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.update(123L, new InsurancePatchBody()));
        verify(insuranceRepository).findById((Long) any());
    }


    @Test
    void testUpdate3() {
        Insurance insurance = mock(Insurance.class);
        when(insurance.getCustomer()).thenReturn(new Customer());
        Optional<Insurance> ofResult = Optional.of(insurance);
        when(insuranceRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.update(123L, new InsurancePatchBody()));
        verify(insuranceRepository).findById((Long) any());
        verify(insurance, atLeast(1)).getCustomer();
    }


    @Test
    void testUpdate4() {
        Insurance insurance = mock(Insurance.class);
        doNothing().when(insurance).setActive(anyBoolean());
        doNothing().when(insurance).setUpdated((LocalDateTime) any());
        when(insurance.getCar()).thenReturn(new Car());
        ArrayList<Driver> drivers = new ArrayList<>();
        when(insurance.getCustomer()).thenReturn(new Customer(123L, "Driver was not found", drivers, new ArrayList<>()));
        Optional<Insurance> ofResult = Optional.of(insurance);
        Insurance insurance1 = new Insurance();
        when(insuranceRepository.save((Insurance) any())).thenReturn(insurance1);
        when(insuranceRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(insurance1, insuranceServiceImpl.update(123L, new InsurancePatchBody()));
        verify(insuranceRepository).save((Insurance) any());
        verify(insuranceRepository).findById((Long) any());
        verify(insurance).getCar();
        verify(insurance, atLeast(1)).getCustomer();
        verify(insurance).setActive(anyBoolean());
        verify(insurance).setUpdated((LocalDateTime) any());
    }


    @Test
    void testUpdate5() {
        Insurance insurance = mock(Insurance.class);
        doThrow(new InsuranceExeption("Not all who wander are lost")).when(insurance).setActive(anyBoolean());
        doThrow(new InsuranceExeption("Not all who wander are lost")).when(insurance).setUpdated((LocalDateTime) any());
        when(insurance.getCar()).thenReturn(new Car());
        ArrayList<Driver> drivers = new ArrayList<>();
        when(insurance.getCustomer()).thenReturn(new Customer(123L, "Driver was not found", drivers, new ArrayList<>()));
        Optional<Insurance> ofResult = Optional.of(insurance);
        when(insuranceRepository.save((Insurance) any())).thenReturn(new Insurance());
        when(insuranceRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.update(123L, new InsurancePatchBody()));
        verify(insuranceRepository).findById((Long) any());
        verify(insurance).getCar();
        verify(insurance, atLeast(1)).getCustomer();
        verify(insurance).setActive(anyBoolean());
    }

    @Test
    void testUpdate6() {
        Insurance insurance = mock(Insurance.class);
        doNothing().when(insurance).setActive(anyBoolean());
        doNothing().when(insurance).setUpdated((LocalDateTime) any());
        when(insurance.getCar()).thenReturn(null);
        ArrayList<Driver> drivers = new ArrayList<>();
        when(insurance.getCustomer()).thenReturn(new Customer(123L, "Driver was not found", drivers, new ArrayList<>()));
        Optional<Insurance> ofResult = Optional.of(insurance);
        when(insuranceRepository.save((Insurance) any())).thenReturn(new Insurance());
        when(insuranceRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.update(123L, new InsurancePatchBody()));
        verify(insuranceRepository).findById((Long) any());
        verify(insurance).getCar();
        verify(insurance, atLeast(1)).getCustomer();
    }

    @Test
    void testUpdate7() {
        Customer customer = mock(Customer.class);
        when(customer.getDrivers()).thenThrow(new InsuranceExeption("Not all who wander are lost"));
        Insurance insurance = mock(Insurance.class);
        doNothing().when(insurance).setActive(anyBoolean());
        doNothing().when(insurance).setUpdated((LocalDateTime) any());
        when(insurance.getCar()).thenReturn(new Car());
        when(insurance.getCustomer()).thenReturn(customer);
        Optional<Insurance> ofResult = Optional.of(insurance);
        when(insuranceRepository.save((Insurance) any())).thenReturn(new Insurance());
        when(insuranceRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.update(123L, new InsurancePatchBody()));
        verify(insuranceRepository).findById((Long) any());
        verify(insurance, atLeast(1)).getCustomer();
        verify(customer).getDrivers();
    }


    @Test
    void testUpdate9() {
        when(insuranceRepository.save((Insurance) any())).thenReturn(new Insurance());
        when(insuranceRepository.findById((Long) any())).thenReturn(Optional.empty());
        new InsuranceExeption("Not all who wander are lost");
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.update(123L, new InsurancePatchBody()));
        verify(insuranceRepository).findById((Long) any());
    }


    @Test
    void testUpdate10() {
        Insurance insurance = mock(Insurance.class);
        doNothing().when(insurance).setActive(anyBoolean());
        doNothing().when(insurance).setUpdated((LocalDateTime) any());
        when(insurance.getCar()).thenReturn(new Car());
        when(insurance.getCustomer()).thenReturn(mock(Customer.class));
        Optional<Insurance> ofResult = Optional.of(insurance);
        when(insuranceRepository.save((Insurance) any())).thenReturn(new Insurance());
        when(insuranceRepository.findById((Long) any())).thenReturn(ofResult);
        new InsuranceExeption("Not all who wander are lost");
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.update(null, new InsurancePatchBody()));
    }

    @Test
    void testUpdate11() {
        Insurance insurance = mock(Insurance.class);
        doNothing().when(insurance).setActive(anyBoolean());
        doNothing().when(insurance).setUpdated((LocalDateTime) any());
        when(insurance.getCar()).thenReturn(new Car());
        when(insurance.getCustomer()).thenReturn(mock(Customer.class));
        Optional<Insurance> ofResult = Optional.of(insurance);
        when(insuranceRepository.save((Insurance) any())).thenReturn(new Insurance());
        when(insuranceRepository.findById((Long) any())).thenReturn(ofResult);
        new InsuranceExeption("Not all who wander are lost");
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.update(123L, null));
    }

    @Test
    void testUpdate12() {
        Customer customer = mock(Customer.class);
        when(customer.getDrivers()).thenReturn(new ArrayList<>());
        Insurance insurance = mock(Insurance.class);
        doNothing().when(insurance).setCustomer((Customer) any());
        doNothing().when(insurance).setCar((Car) any());
        doNothing().when(insurance).setActive(anyBoolean());
        doNothing().when(insurance).setUpdated((LocalDateTime) any());
        when(insurance.getCar()).thenReturn(new Car());
        when(insurance.getCustomer()).thenReturn(customer);
        Optional<Insurance> ofResult = Optional.of(insurance);
        Insurance insurance1 = new Insurance();
        when(insuranceRepository.save((Insurance) any())).thenReturn(insurance1);
        when(insuranceRepository.findById((Long) any())).thenReturn(ofResult);
        new InsuranceExeption("Not all who wander are lost");
        when(customerRepository.findById((Long) any())).thenReturn(Optional.of(new Customer()));
        when(carRepository.findById((Long) any())).thenReturn(Optional.of(new Car()));
        assertSame(insurance1, insuranceServiceImpl.update(123L, new InsurancePatchBody(123L, 123L, true)));
        verify(insuranceRepository).save((Insurance) any());
        verify(insuranceRepository).findById((Long) any());
        verify(insurance).getCar();
        verify(insurance, atLeast(1)).getCustomer();
        verify(insurance).setActive(anyBoolean());
        verify(insurance).setCar((Car) any());
        verify(insurance).setCustomer((Customer) any());
        verify(insurance).setUpdated((LocalDateTime) any());
        verify(customer).getDrivers();
        verify(customerRepository).findById((Long) any());
        verify(carRepository).findById((Long) any());
    }

    @Test
    void testUpdate13() {
        Customer customer = mock(Customer.class);
        when(customer.getDrivers()).thenReturn(new ArrayList<>());
        Insurance insurance = mock(Insurance.class);
        doThrow(new InsuranceExeption("Not all who wander are lost")).when(insurance).setCustomer((Customer) any());
        doNothing().when(insurance).setCar((Car) any());
        doNothing().when(insurance).setActive(anyBoolean());
        doNothing().when(insurance).setUpdated((LocalDateTime) any());
        when(insurance.getCar()).thenReturn(new Car());
        when(insurance.getCustomer()).thenReturn(customer);
        Optional<Insurance> ofResult = Optional.of(insurance);
        when(insuranceRepository.save((Insurance) any())).thenReturn(new Insurance());
        when(insuranceRepository.findById((Long) any())).thenReturn(ofResult);
        new InsuranceExeption("Not all who wander are lost");
        when(customerRepository.findById((Long) any())).thenReturn(Optional.of(new Customer()));
        when(carRepository.findById((Long) any())).thenReturn(Optional.of(new Car()));
        assertThrows(InsuranceExeption.class,
                () -> insuranceServiceImpl.update(123L, new InsurancePatchBody(123L, 123L, true)));
        verify(insuranceRepository).findById((Long) any());
        verify(insurance).getCar();
        verify(insurance, atLeast(1)).getCustomer();
        verify(insurance).setActive(anyBoolean());
        verify(insurance).setCar((Car) any());
        verify(insurance).setCustomer((Customer) any());
        verify(customer).getDrivers();
        verify(customerRepository).findById((Long) any());
        verify(carRepository).findById((Long) any());
    }


    @Test
    void testUpdate16() {
        Customer customer = mock(Customer.class);
        when(customer.getDrivers()).thenReturn(new ArrayList<>());
        Insurance insurance = mock(Insurance.class);
        doNothing().when(insurance).setCustomer((Customer) any());
        doNothing().when(insurance).setCar((Car) any());
        doNothing().when(insurance).setActive(anyBoolean());
        doNothing().when(insurance).setUpdated((LocalDateTime) any());
        when(insurance.getCar()).thenReturn(new Car());
        when(insurance.getCustomer()).thenReturn(customer);
        Optional<Insurance> ofResult = Optional.of(insurance);
        when(insuranceRepository.save((Insurance) any())).thenReturn(new Insurance());
        when(insuranceRepository.findById((Long) any())).thenReturn(ofResult);
        new InsuranceExeption("Not all who wander are lost");
        when(customerRepository.findById((Long) any())).thenReturn(null);
        when(carRepository.findById((Long) any())).thenReturn(null);
        InsurancePatchBody insurancePatchBody = mock(InsurancePatchBody.class);
        when(insurancePatchBody.isActive()).thenThrow(new InsuranceExeption("Not all who wander are lost"));
        when(insurancePatchBody.getCarId()).thenThrow(new InsuranceExeption("Not all who wander are lost"));
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.update(123L, insurancePatchBody));
        verify(insuranceRepository).findById((Long) any());
        verify(insurance).getCar();
        verify(insurance, atLeast(1)).getCustomer();
        verify(customer).getDrivers();
        verify(insurancePatchBody).isActive();
    }


    @Test
    void testDelete() {
        when(insuranceRepository.findById((Long) any())).thenReturn(Optional.of(new Insurance()));
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.delete(123L));
        verify(insuranceRepository).findById((Long) any());
    }


    @Test
    void testDelete2() {
        LocalDate creation = LocalDate.ofEpochDay(1L);
        LocalDateTime updated = LocalDateTime.of(1, 1, 1, 1, 1);
        Customer customer = new Customer();
        when(insuranceRepository.findById((Long) any()))
                .thenReturn(Optional.of(new Insurance(123L, creation, updated, true, customer, new Car())));
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.delete(123L));
        verify(insuranceRepository).findById((Long) any());
    }


    @Test
    void testDelete3() {
        Insurance insurance = mock(Insurance.class);
        when(insurance.getCustomer()).thenReturn(new Customer());
        Optional<Insurance> ofResult = Optional.of(insurance);
        when(insuranceRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.delete(123L));
        verify(insuranceRepository).findById((Long) any());
        verify(insurance, atLeast(1)).getCustomer();
    }


    @Test
    void testDelete4() {
        Insurance insurance = mock(Insurance.class);
        doNothing().when(insurance).setActive(anyBoolean());
        doNothing().when(insurance).setUpdated((LocalDateTime) any());
        when(insurance.getCar()).thenReturn(new Car());
        ArrayList<Driver> drivers = new ArrayList<>();
        when(insurance.getCustomer()).thenReturn(new Customer(123L, "Driver was not found", drivers, new ArrayList<>()));
        Optional<Insurance> ofResult = Optional.of(insurance);
        when(insuranceRepository.save((Insurance) any())).thenReturn(new Insurance());
        when(insuranceRepository.findById((Long) any())).thenReturn(ofResult);
        insuranceServiceImpl.delete(123L);
        verify(insuranceRepository).save((Insurance) any());
        verify(insuranceRepository).findById((Long) any());
        verify(insurance).getCar();
        verify(insurance, atLeast(1)).getCustomer();
        verify(insurance).setActive(anyBoolean());
        verify(insurance).setUpdated((LocalDateTime) any());
    }


    @Test
    void testDelete5() {
        Insurance insurance = mock(Insurance.class);
        doThrow(new InsuranceExeption("Not all who wander are lost")).when(insurance).setActive(anyBoolean());
        doThrow(new InsuranceExeption("Not all who wander are lost")).when(insurance).setUpdated((LocalDateTime) any());
        when(insurance.getCar()).thenReturn(new Car());
        ArrayList<Driver> drivers = new ArrayList<>();
        when(insurance.getCustomer()).thenReturn(new Customer(123L, "Driver was not found", drivers, new ArrayList<>()));
        Optional<Insurance> ofResult = Optional.of(insurance);
        when(insuranceRepository.save((Insurance) any())).thenReturn(new Insurance());
        when(insuranceRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.delete(123L));
        verify(insuranceRepository).findById((Long) any());
        verify(insurance).getCar();
        verify(insurance, atLeast(1)).getCustomer();
        verify(insurance).setUpdated((LocalDateTime) any());
    }


    @Test
    void testDelete6() {
        Insurance insurance = mock(Insurance.class);
        doNothing().when(insurance).setActive(anyBoolean());
        doNothing().when(insurance).setUpdated((LocalDateTime) any());
        when(insurance.getCar()).thenReturn(null);
        ArrayList<Driver> drivers = new ArrayList<>();
        when(insurance.getCustomer()).thenReturn(new Customer(123L, "Driver was not found", drivers, new ArrayList<>()));
        Optional<Insurance> ofResult = Optional.of(insurance);
        when(insuranceRepository.save((Insurance) any())).thenReturn(new Insurance());
        when(insuranceRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.delete(123L));
        verify(insuranceRepository).findById((Long) any());
        verify(insurance).getCar();
        verify(insurance, atLeast(1)).getCustomer();
    }


    @Test
    void testDelete7() {
        Customer customer = mock(Customer.class);
        when(customer.getDrivers()).thenThrow(new InsuranceExeption("Not all who wander are lost"));
        Insurance insurance = mock(Insurance.class);
        doNothing().when(insurance).setActive(anyBoolean());
        doNothing().when(insurance).setUpdated((LocalDateTime) any());
        when(insurance.getCar()).thenReturn(new Car());
        when(insurance.getCustomer()).thenReturn(customer);
        Optional<Insurance> ofResult = Optional.of(insurance);
        when(insuranceRepository.save((Insurance) any())).thenReturn(new Insurance());
        when(insuranceRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.delete(123L));
        verify(insuranceRepository).findById((Long) any());
        verify(insurance, atLeast(1)).getCustomer();
        verify(customer).getDrivers();
    }


    @Test
    void testDelete9() {
        when(insuranceRepository.save((Insurance) any())).thenReturn(new Insurance());
        when(insuranceRepository.findById((Long) any())).thenReturn(Optional.empty());
        new InsuranceExeption("Not all who wander are lost");
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.delete(123L));
        verify(insuranceRepository).findById((Long) any());
    }

    @Test
    void testGetInsurance() {
        when(insuranceRepository.findById((Long) any())).thenReturn(Optional.of(new Insurance()));
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.getInsurance(123L));
        verify(insuranceRepository).findById((Long) any());
    }

    @Test
    void testGetInsurance2() {
        LocalDate creation = LocalDate.ofEpochDay(1L);
        LocalDateTime updated = LocalDateTime.of(1, 1, 1, 1, 1);
        Customer customer = new Customer();
        when(insuranceRepository.findById((Long) any()))
                .thenReturn(Optional.of(new Insurance(123L, creation, updated, true, customer, new Car())));
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.getInsurance(123L));
        verify(insuranceRepository).findById((Long) any());
    }

    @Test
    void testGetInsurance3() {
        Insurance insurance = mock(Insurance.class);
        when(insurance.getCustomer()).thenReturn(new Customer());
        Optional<Insurance> ofResult = Optional.of(insurance);
        when(insuranceRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.getInsurance(123L));
        verify(insuranceRepository).findById((Long) any());
        verify(insurance, atLeast(1)).getCustomer();
    }

    @Test
    void testGetInsurance6() {
        Car car = new Car();
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        car.setFipeValue(valueOfResult);
        Insurance insurance = mock(Insurance.class);
        when(insurance.isActive()).thenReturn(true);
        when(insurance.getCar()).thenReturn(car);
        ArrayList<Driver> driverList = new ArrayList<>();
        when(insurance.getCustomer()).thenReturn(new Customer(123L, "Driver was not found", driverList, new ArrayList<>()));
        Optional<Insurance> ofResult = Optional.of(insurance);
        when(insuranceRepository.findById((Long) any())).thenReturn(ofResult);
        InsuranceResponse actualInsurance = insuranceServiceImpl.getInsurance(123L);
        assertTrue(actualInsurance.isActive());
        assertEquals("Driver was not found", actualInsurance.getCustomerName());
        assertEquals(8, actualInsurance.getPercentValue().intValue());
        assertEquals(driverList, actualInsurance.getDrivers());
        assertEquals("3.36", actualInsurance.getValueInsurance().toString());
        CarResponse car1 = actualInsurance.getCar();
        assertNull(car1.getModel());
        assertNull(car1.getYearCar());
        BigDecimal fipeValue = car1.getFipeValue();
        assertSame(valueOfResult, fipeValue);
        assertNull(car1.getManufacturer());
        assertEquals("42", fipeValue.toString());
        verify(insuranceRepository).findById((Long) any());
        verify(insurance).isActive();
        verify(insurance, atLeast(1)).getCar();
        verify(insurance, atLeast(1)).getCustomer();
    }

    @Test
    void testGetInsurance9() {
        when(insuranceRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.getInsurance(123L));
        verify(insuranceRepository).findById((Long) any());
    }

    @Test
    void testGetInsurance10() {
        Car car = new Car();
        car.setFipeValue(BigDecimal.valueOf(42L));
        Insurance insurance = mock(Insurance.class);
        when(insurance.isActive()).thenThrow(new InsuranceExeption("Not all who wander are lost"));
        when(insurance.getCar()).thenReturn(car);
        ArrayList<Driver> drivers = new ArrayList<>();
        when(insurance.getCustomer()).thenReturn(new Customer(123L, "Driver was not found", drivers, new ArrayList<>()));
        Optional<Insurance> ofResult = Optional.of(insurance);
        when(insuranceRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(InsuranceExeption.class, () -> insuranceServiceImpl.getInsurance(123L));
        verify(insuranceRepository).findById((Long) any());
        verify(insurance).isActive();
        verify(insurance, atLeast(1)).getCar();
        verify(insurance, atLeast(1)).getCustomer();
    }
}

