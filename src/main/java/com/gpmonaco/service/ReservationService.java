package com.gpmonaco.service;

import com.gpmonaco.dto.ReservationDTO;
import com.gpmonaco.dto.ReservationPriceDTO;
import com.gpmonaco.entities.Reservation;

import java.util.List;

public interface ReservationService {

    public List<ReservationDTO> getReservations();
    public ReservationDTO createReservation(ReservationDTO reservationDTO);

    public ReservationDTO updateReservation(Long id, Reservation reservation);

    public ReservationDTO isAuthorizedToUpdateReservation(String token, String email);

    public void deleteReservation(Long id);

    public ReservationPriceDTO getEstimatePrice(ReservationPriceDTO reservationPriceDTO);

}
