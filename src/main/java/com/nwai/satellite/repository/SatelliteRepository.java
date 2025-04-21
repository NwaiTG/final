package com.nwai.satellite.repository;

import com.nwai.satellite.model.Satellite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SatelliteRepository extends JpaRepository<Satellite, Long> {
}
