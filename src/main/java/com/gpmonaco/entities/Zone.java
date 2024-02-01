package com.gpmonaco.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "zone")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "zone")
    private ZoneFeatures features;

    @OneToMany(mappedBy = "zone")
    List<DailyPlan> dailyPlans;

}
