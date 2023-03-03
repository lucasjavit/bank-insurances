package com.lucasit.insurances.mapper;

import com.lucasit.insurances.model.Car;
import com.lucasit.insurances.request.CarPostBody;
import com.lucasit.insurances.request.CarResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CarMapper {

    public static final CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    public abstract Car toEntity(CarPostBody carPostBody);

    public abstract CarResponse toDTO(Car car);
}
