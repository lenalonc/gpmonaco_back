package com.gpmonaco.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "daily_plan")
public class DailyPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idzone")
    private Zone zone;

    @ManyToOne
    @JoinColumn(name = "idday")
    private Day day;

    @Column(name = "capacity")
    private int capacity;

    @OneToMany(mappedBy = "dailyPlan")
    private List<Ticket> tickets;

    @Override
    public String toString() {
        return "DailyPlan{" +
                "id=" + id +
                ", zone=" + zone +
                ", day=" + day +
                ", capacity=" + capacity +
                '}';
    }

    public void reduceCapacity(int q){
        capacity -= q;
    }

    public void addCapacity(int q){
        capacity += q;
    }
}
