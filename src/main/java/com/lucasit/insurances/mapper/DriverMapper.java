package com.lucasit.insurances.mapper;

import com.lucasit.insurances.model.Driver;
import com.lucasit.insurances.request.DriverPostBody;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DriverMapper {

    public static final DriverMapper INSTANCE = Mappers.getMapper(DriverMapper.class);

    public abstract Driver toDTO(DriverPostBody driverPostBody);

    public abstract List<Driver> ListDTOToListEntity(List<DriverPostBody> driverPostBodyList);

}
