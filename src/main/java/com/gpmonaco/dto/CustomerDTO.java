package com.gpmonaco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {

    private Long id;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    private String company;

    @NotBlank
    private String address1;

    private String address2;

    @NotNull
    private int postal_code;

    @NotBlank
    private String place;

    @NotBlank
    private String country;

    @NotBlank
    private String email;

    //@JsonManagedReference
    //private List<ReservationDTO> reservations;

}
