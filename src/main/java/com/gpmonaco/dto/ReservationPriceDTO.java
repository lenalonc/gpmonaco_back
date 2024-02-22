package com.gpmonaco.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ReservationPriceDTO {

        private Date date;

        private Double price;

        private Double discount;

        @NotNull
        private List<TicketDTO> tickets;

}
