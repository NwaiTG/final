package com.nwai.satellite.service.impl;

import com.nwai.satellite.dto.response.AstronautResponseDto;
import com.nwai.satellite.dto.resquest.AstronautRequestDto;
import com.nwai.satellite.exception.ResourceNotFoundException;
import com.nwai.satellite.mapper.AstronautMapper;
import com.nwai.satellite.model.Astronaut;
import com.nwai.satellite.model.Satellite;
import com.nwai.satellite.repository.AstronautRepository;
import com.nwai.satellite.repository.SatelliteRepository;
import com.nwai.satellite.service.AstronautService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AstronautServiceImpl implements AstronautService {
    private final AstronautRepository astronautRepository;
    private final SatelliteRepository satelliteRepository;
    private final AstronautMapper astronautMapper;

    @Override
    public Page<AstronautResponseDto> searchAstronautByExpYear(int expYear, int page, int pageSize, String sortDirection, String sortField) {
        Pageable pageable = PageRequest.of(
                page,
                pageSize,
                Sort.Direction.fromString(sortDirection),
                sortField
        );
        Page<Astronaut> astronautPage = astronautRepository.findByExpYear(expYear, pageable);
        Page<AstronautResponseDto> astronautResponseDtoPage = astronautPage.map(astronaut -> astronautMapper.astronautToAstroautResponseDto(astronaut));
        return astronautResponseDtoPage;
    }

    @Override
    public AstronautResponseDto createAstronaut(AstronautRequestDto dto) {
            Astronaut astronaut = astronautMapper.astronautRequestDtoToAstronaut(dto);

        List<Satellite> satellites = dto.satelliteList().stream()
                .map(satDto -> Optional.ofNullable(satDto.getId())
                        .flatMap(satelliteRepository::findById)
                        .orElseGet(() -> satelliteRepository.save(new Satellite(
                                satDto.getSatelliteName(),
                                satDto.getLaunchDate(),
                                satDto.getOrbitType(),
                                satDto.isDecommissioned()
                        )))
                )
                .collect(Collectors.toList());  // ← changed from toSet()

        astronaut.setSatelliteList(satellites);

            Astronaut saved = astronautRepository.save(astronaut);
            return astronautMapper.astronautToAstroautResponseDto(saved);

    }

    @Override
    public AstronautResponseDto updateAstronaut(Long id, AstronautRequestDto dto) {
            Astronaut astronaut = astronautRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Astronaut not found with id: " + id));

            // Update basic fields
            astronaut.setFirstName(dto.firstName());
            astronaut.setLastName(dto.lastName());
            astronaut.setExpYear(dto.expYear());

            // Rebuild satellite list
            List<Satellite> satellites = dto.satelliteList().stream()
                    .map(satDto -> Optional.ofNullable(satDto.getId())
                            .flatMap(satelliteRepository::findById)
                            .orElseGet(() -> satelliteRepository.save(new Satellite(
                                    satDto.getSatelliteName(),
                                    satDto.getLaunchDate(),
                                    satDto.getOrbitType(),
                                    satDto.isDecommissioned()
                            )))
                    )
                    .collect(Collectors.toList());

            astronaut.setSatelliteList(satellites);

            Astronaut saved = astronautRepository.save(astronaut);
            return astronautMapper.astronautToAstroautResponseDto(saved);
    }

    @Override
    public void deleteAstronaut(Long id) {
        Optional<Astronaut> astronaut = astronautRepository.findById(id);
        if (astronaut.isPresent()) {
            astronautRepository.delete(astronaut.get());
        }
    }

    @Override
    public Optional<AstronautResponseDto> getAstronautById(Long id) {
        return Optional.empty();
    }
}
