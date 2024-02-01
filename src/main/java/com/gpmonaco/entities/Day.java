package com.gpmonaco.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gpmonaco.dto.DailyPlanDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="day")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @OneToMany(mappedBy = "day")
    List<DailyPlan> dailyPlans;
}
