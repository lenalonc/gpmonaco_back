package com.gpmonaco.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DayDTO {

    private Long id;

    private Date date;

//    @JsonBackReference(value = "day-plan")
    //private DailyPlanDTO dailyPlan;

}
