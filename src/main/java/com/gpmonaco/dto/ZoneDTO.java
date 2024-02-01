package com.gpmonaco.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ZoneDTO {

    private Long id;

    @JsonManagedReference
    private ZoneFeaturesDTO features;

//    @JsonManagedReference
//    private List<DailyPlanDTO> dailyPlans;

}
