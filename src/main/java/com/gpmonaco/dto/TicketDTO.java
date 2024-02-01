package com.gpmonaco.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TicketDTO {

    private Long id;

    @JsonBackReference
    private DailyPlanDTO dailyPlan;

    @JsonBackReference
    private ReservationDTO reservation;

}
