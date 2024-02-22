package com.gpmonaco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PromoCodeDTO {

    private Long id;

    private String code;

    private boolean active;

}
