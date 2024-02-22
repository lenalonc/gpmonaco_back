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

//    @JsonBackReference(value = "plan-ticket")
    private DailyPlanDTO dailyPlan;

    private int quantity;

//    @JsonBackReference(value = "reservation-ticket")
//    private ReservationDTO reservation;

}
