package com.nwai.satellite.controller;

import com.nwai.satellite.dto.response.SatelliteResponseDto;
import com.nwai.satellite.dto.resquest.SatelliteRequestDto;
import com.nwai.satellite.service.SatelliteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/satellites")
@RequiredArgsConstructor
public class SatelliteController {
    private final SatelliteService satelliteService;
    @PutMapping("/{id}")
    public ResponseEntity<SatelliteResponseDto> updateSatellite(@PathVariable Long id, @RequestBody SatelliteRequestDto satelliteRequestDto) {
        return satelliteService.updateSatellite(id, satelliteRequestDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
