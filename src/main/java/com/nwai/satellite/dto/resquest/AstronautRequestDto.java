package com.nwai.satellite.dto.resquest;

import com.nwai.satellite.model.Satellite;
import jakarta.validation.constraints.*;

import java.util.List;

public record AstronautRequestDto(
        Long id,
        @Size(min = 2, max = 20, message = "Must be between 2 to 20 chars")
        @NotBlank(message = "Satellite name should not be blank/null/empty")
        String firstName,
        @Size(min = 2, max = 20, message = "Must be between 2 to 20 chars")
        @NotBlank(message = "Satellite name should not be blank/null/empty")
        String lastName,
        @Min(value = 0, message = "Experience must be at least 0 years")
        @Max(value = 50, message = "Experience must not exceed 50 years")
        @NotNull(message = "Experience is required")
        int expYear,
        List<Satellite> satelliteList
) {
}

/// private Long id;
///     @Column(nullable = false)
///     private String firstName;
///     @Column(nullable = false)
///     private String lastName;
///     @Column(nullable = false)
///     private int expYear;
///
///     @ManyToMany(cascade = CascadeType.ALL)
///     @JoinTable(
///             name = "astronaut_satellite",
///             joinColumns = @JoinColumn(name = "astronaut_id"),
///             inverseJoinColumns = @JoinColumn(name = "satellite_id")
///     )
///     private List<Satellite> satelliteList;
