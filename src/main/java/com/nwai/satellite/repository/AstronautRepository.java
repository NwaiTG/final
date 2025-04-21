package com.nwai.satellite.repository;

import com.nwai.satellite.model.Astronaut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AstronautRepository extends JpaRepository<Astronaut, Long> {
    Page<Astronaut> findByExpYear(int expYear, Pageable pageable);
}
