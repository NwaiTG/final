package com.nwai.satellite.controller;

import com.nwai.satellite.dto.response.AstronautResponseDto;
import com.nwai.satellite.dto.resquest.AstronautRequestDto;
import com.nwai.satellite.service.AstronautService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/astronauts")
@RequiredArgsConstructor
public class AstronautController {

    private final AstronautService astronautService;

    @PostMapping
    public ResponseEntity<AstronautResponseDto> createAstronaut(@Valid @RequestBody AstronautRequestDto astronautRequestDto) {
        AstronautResponseDto astronautResponseDto = astronautService.createAstronaut(astronautRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(astronautResponseDto);
    }

    @GetMapping("")
    public ResponseEntity<Page<AstronautResponseDto>> getAllAppointments(
            @RequestParam int page,
            @RequestParam int pageSize,
            @RequestParam String sortDirection,
            @RequestParam String sortBy,
            @RequestParam int expYears
    ) {
        Page<AstronautResponseDto> astronautResponseDtoPage = astronautService.searchAstronautByExpYear(expYears, page, pageSize, sortDirection, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(astronautResponseDtoPage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAstronaut(@PathVariable Long id) {
        astronautService.deleteAstronaut(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
