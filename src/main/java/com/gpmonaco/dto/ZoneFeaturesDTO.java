package com.gpmonaco.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ZoneFeaturesDTO {

//    @JsonBackReference(value = "zone-features")
//    private ZoneDTO zone;

    private String name;

    private int capacity;

    private Double price;

    private boolean tv;

    private boolean dsb;

}
