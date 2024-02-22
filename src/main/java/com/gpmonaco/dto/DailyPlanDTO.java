package com.gpmonaco.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.gpmonaco.entities.Day;
import com.gpmonaco.entities.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class DailyPlanDTO {

    private Long id;

//    @JsonManagedReference(value = "day-plan")
    private DayDTO day;

//    @JsonBackReference //(value = "plan-zone")
    private ZoneDTO zone;

    private int capacity;

//    @JsonBackReference(value = "plan-ticket")
//    private List<TicketDTO> tickets;

}
