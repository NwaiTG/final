package com.nwai.satellite.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Astronauts")
@Data
@NoArgsConstructor
public class Astronaut {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "astronaut_id")
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private int expYear;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "astronaut_satellite",
            joinColumns = @JoinColumn(name = "astronaut_id"),
            inverseJoinColumns = @JoinColumn(name = "satellite_id")
    )
    private List<Satellite> satelliteList;
}
