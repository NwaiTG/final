package com.nwai.satellite.mapper;

import com.nwai.satellite.dto.response.AstronautResponseDto;
import com.nwai.satellite.dto.resquest.AstronautRequestDto;
import com.nwai.satellite.model.Astronaut;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AstronautMapper {

    Astronaut astronautRequestDtoToAstronaut(AstronautRequestDto astronautRequestDto);
    AstronautResponseDto astronautToAstroautResponseDto(Astronaut astronaut);
}
