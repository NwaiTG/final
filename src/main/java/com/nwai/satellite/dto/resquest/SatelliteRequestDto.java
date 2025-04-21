package com.nwai.satellite.dto.resquest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nwai.satellite.model.Astronaut;
import com.nwai.satellite.model.OrbitType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record SatelliteRequestDto(
        Long id,
        @NotBlank(message = "Satellite name should not be blank/null/empty")
        String satelliteName,
        @JsonFormat(pattern = "yyyy-MM-dd")
        @Past(message = "Launch date must be in the past")
        @NotNull(message = "Launch date is required")
        LocalDate launchDate,
        OrbitType orbitType,
        boolean decommissioned,
        List<Astronaut> astronautList
) {
}

/// private Integer id;
///     private String satelliteName;
///     private LocalDate launchDate;
///     @Enumerated(EnumType.STRING)
///     private OrbitType orbitType;
///     @ManyToMany(fetch = FetchType.LAZY, mappedBy = "satelliteList")
///     private List<Astronaut>  astronautList;
