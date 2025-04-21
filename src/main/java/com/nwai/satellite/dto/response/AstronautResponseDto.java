package com.nwai.satellite.dto.response;

import com.nwai.satellite.model.Satellite;

import java.util.List;

public record AstronautResponseDto(
        String firstName,
        String lastName,
        int expYear,
        List<Satellite> satelliteList
) {
}
