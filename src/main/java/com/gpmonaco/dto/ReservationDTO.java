package com.gpmonaco.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ReservationDTO {

    private Long id;

    @NotNull
    private Date date;

    @NotNull
    private Long price;

    private Long discount;

    @JsonManagedReference
    @NotNull
    private List<TicketDTO> tickets;

    private String token;

    //JsonBackReference
    //@NotNull
   // private CustomerDTO customer;

}
