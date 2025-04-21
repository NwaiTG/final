package com.nwai.satellite.dto.response;

import com.nwai.satellite.model.Astronaut;
import com.nwai.satellite.model.OrbitType;

import java.time.LocalDate;
import java.util.List;

public record SatelliteResponseDto(
        String satelliteName,
        LocalDate launchDate,
        OrbitType orbitType,
        List<Astronaut> astronautList
) {
}
