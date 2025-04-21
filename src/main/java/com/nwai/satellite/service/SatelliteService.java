package com.nwai.satellite.service;

import com.nwai.satellite.dto.response.SatelliteResponseDto;
import com.nwai.satellite.dto.resquest.SatelliteRequestDto;

import java.util.Optional;

public interface SatelliteService {
    Optional<SatelliteResponseDto> updateSatellite(Long id, SatelliteRequestDto dto);
}
