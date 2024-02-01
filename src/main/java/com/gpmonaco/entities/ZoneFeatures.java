package com.gpmonaco.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "zone_features")
@Data
public class ZoneFeatures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "idzone")
    private Zone zone;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "TV", nullable = false)
    private boolean tv;

    @Column(name = "disabled_access", nullable = false)
    private boolean dsb;

}
