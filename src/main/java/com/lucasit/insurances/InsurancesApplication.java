package com.lucasit.insurances;

import com.lucasit.insurances.mapper.CarMapper;
import com.lucasit.insurances.repository.CarRepository;
import com.lucasit.insurances.request.CarPostBody;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
@RequiredArgsConstructor
public class InsurancesApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(InsurancesApplication.class, args);
    }

    private final CarRepository carRepository;

    @Override
    public void run(ApplicationArguments args) {

        CarPostBody carPostBody = CarPostBody.builder()
                .model("Fiesta")
                .fipeValue(new BigDecimal(51206).setScale(2))
                .manufacturer("Ford")
                .yearCar("2017/2017")
                .build();

        CarPostBody carPostBody2 = CarPostBody.builder()
                .model("Gol")
                .fipeValue(new BigDecimal(66493).setScale(2))
                .manufacturer("Volkswagen")
                .yearCar("2017/2017")
                .build();

        carRepository.save(CarMapper.INSTANCE.toEntity(carPostBody));
        carRepository.save(CarMapper.INSTANCE.toEntity(carPostBody2));

    }
}
