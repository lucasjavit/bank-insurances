package com.lucasit.insurances.controller;


import com.lucasit.insurances.model.Insurance;
import com.lucasit.insurances.request.InsurancePostBody;
import com.lucasit.insurances.service.InsuranceService;
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
@RequestMapping("insurances/budget")
public class InsuranceController {

    private final InsuranceService insuranceService;

    @PostMapping
    public ResponseEntity<Insurance> save(@RequestBody @Valid InsurancePostBody insurancePostBody) {
        return new ResponseEntity<>(insuranceService.save(insurancePostBody), HttpStatus.CREATED);
    }

}
