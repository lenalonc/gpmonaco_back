package com.gpmonaco.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private Date date;

    private Double price;

    private Double discount;

//    @JsonManagedReference(value = "reservation-ticket")
    @NotNull
    private List<TicketDTO> tickets;

    private String token;

    @JsonBackReference(value = "customer-reservation")
    @NotNull
    private CustomerDTO customer;

}
