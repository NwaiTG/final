package com.nwai.satellite.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Satellite")
@Data
@NoArgsConstructor
public class Satellite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "satellite_id")
    private Long id;
    @Column(unique = true)
    private String satelliteName;
    private LocalDate launchDate;
    @Enumerated(EnumType.STRING)
    private OrbitType orbitType;
    private boolean decommissioned;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "satelliteList")
    private List<Astronaut>  astronautList;

    public Satellite(String satelliteName, LocalDate launchDate, OrbitType orbitType, boolean decommissioned) {
        this.satelliteName = satelliteName;
        this.launchDate = launchDate;
        this.orbitType = orbitType;
        this.decommissioned = decommissioned;
    }
}
