package com.nwai.satellite.mapper;

import com.nwai.satellite.dto.response.SatelliteResponseDto;
import com.nwai.satellite.dto.resquest.SatelliteRequestDto;
import com.nwai.satellite.model.Satellite;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SatelliteMapper {
    Satellite satelliteRequestToSatellite(SatelliteRequestDto satelliteRequestDto);
    SatelliteResponseDto satelliteToSatelliteResponseDto(Satellite satellite);
}
