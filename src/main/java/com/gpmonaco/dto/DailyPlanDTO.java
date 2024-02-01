package com.gpmonaco.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gpmonaco.entities.Day;
import com.gpmonaco.entities.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DailyPlanDTO {

    private Long id;

    @JsonManagedReference
    private ZoneDTO zone;

    @JsonManagedReference
    private DayDTO day;

    private int capacity;

   @JsonManagedReference
   private List<TicketDTO> tickets;

}
