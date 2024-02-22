package com.gpmonaco.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "daily_plan" , nullable = false)
    private DailyPlan dailyPlan;


    @ManyToOne
    @JoinColumn(name = "reservation", nullable = false)
    private Reservation reservation;

    @Column(name = "quantity", nullable = false)
    private int quantity;

}
