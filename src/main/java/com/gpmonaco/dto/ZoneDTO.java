package com.gpmonaco.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idZ")
public class ZoneDTO {

    private Long id;

//    @JsonManagedReference(value = "zone-features")
    private ZoneFeaturesDTO features;

  //  @JsonManagedReference //(value = "plan-zone")
   // private List<DailyPlanDTO> dailyPlans;

    @Override
    public String toString() {
        return "ZoneDTO{" +
                "id=" + id +
                ", features=" + features +
                '}';
    }
}
