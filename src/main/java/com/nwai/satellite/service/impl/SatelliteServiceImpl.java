package com.nwai.satellite.service.impl;

import com.nwai.satellite.dto.response.SatelliteResponseDto;
import com.nwai.satellite.dto.resquest.SatelliteRequestDto;
import com.nwai.satellite.exception.ResourceNotFoundException;
import com.nwai.satellite.mapper.SatelliteMapper;
import com.nwai.satellite.model.Satellite;
import com.nwai.satellite.repository.SatelliteRepository;
import com.nwai.satellite.service.SatelliteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SatelliteServiceImpl implements SatelliteService {
    private final SatelliteRepository satelliteRepository;
    private final SatelliteMapper satelliteMapper;

    @Override
    public Optional<SatelliteResponseDto> updateSatellite(Long id, SatelliteRequestDto dto) {
        Optional<Satellite> existing = satelliteRepository.findById(id);
        if (existing.isPresent()) {
            Satellite satellite = existing.get();
            if(satellite.isDecommissioned() == false){
                satellite.setSatelliteName(dto.satelliteName());
                satellite.setDecommissioned(dto.decommissioned());
                satellite.setLaunchDate(dto.launchDate());
                satellite.setOrbitType(dto.orbitType());
            }
            return Optional.ofNullable(satelliteRepository.save(satellite))
                    .map(satelliteMapper::satelliteToSatelliteResponseDto);

        }else {
            throw new ResourceNotFoundException("Satellite not found");
        }
    }
}
