package com.lucasit.insurances.controller;


import com.lucasit.insurances.model.Customer;
import com.lucasit.insurances.request.CustomerPostBody;
import com.lucasit.insurances.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody @Valid CustomerPostBody customerPostBody) {
        return new ResponseEntity<>(customerService.save(customerPostBody), HttpStatus.CREATED);
    }
}
