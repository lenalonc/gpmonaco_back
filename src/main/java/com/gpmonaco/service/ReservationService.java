package com.gpmonaco.service;

import com.gpmonaco.dto.ReservationDTO;
import com.gpmonaco.entities.Reservation;

public interface ReservationService {
    public ReservationDTO createReservation(ReservationDTO reservationDTO);
}
