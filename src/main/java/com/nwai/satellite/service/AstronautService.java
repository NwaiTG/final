package com.nwai.satellite.service;

import com.nwai.satellite.dto.response.AstronautResponseDto;
import com.nwai.satellite.dto.resquest.AstronautRequestDto;
import com.nwai.satellite.model.Astronaut;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface AstronautService {
    Page<AstronautResponseDto> searchAstronautByExpYear(int expYear, int page, int pageSize, String sortDirection, String sortField);

    AstronautResponseDto createAstronaut(AstronautRequestDto dto);

    AstronautResponseDto updateAstronaut(Long id, AstronautRequestDto dto);

    void deleteAstronaut(Long id);

    Optional<AstronautResponseDto> getAstronautById(Long id);
}
