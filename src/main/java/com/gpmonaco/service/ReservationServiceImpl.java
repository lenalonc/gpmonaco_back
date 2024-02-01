package com.gpmonaco.service;

import com.gpmonaco.dto.ReservationDTO;
import com.gpmonaco.entities.Day;
import com.gpmonaco.entities.Ticket;
import com.gpmonaco.entities.Reservation;
import com.gpmonaco.repository.TicketRepository;
import com.gpmonaco.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import util.JwtUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    ReservationRepository reservationRepository;
    TicketRepository ticketRepository;
    ModelMapper mapper;
    JwtUtil jwtUtil;

    @Override
    public Reservation createReservation(Reservation reservationDTO) {
        Reservation reservation = mapper.map(reservationDTO, Reservation.class);
        List<Ticket> karte = reservation.getTickets();
        karte.forEach(karta -> karta.setReservation(reservation));
        reservation.setDate(new Date());
        long price = karte.stream().mapToLong(karta -> karta.getDailyPlan().getZone().getFeatures().getPrice()).sum();
        reservation.setPrice(price);
        String token = jwtUtil.generateToken(reservation);
        reservation.setToken(token);
        checkDiscount(reservation);
        if(reservation.getDiscount() != 0){
            reservation.setPrice(reservation.getPrice() * (100 - reservation.getDiscount())/100);
        }

        reservationRepository.save(reservation);
        ticketRepository.saveAll(karte);
        return reservation;
        //return mapper.map(reservationRepository.save(reservation), ReservationDTO.class);
    }

    private void checkDiscount(Reservation rezervacija) {
        Date date = new Date(124, 2, 16);
        rezervacija.setDiscount(0L);
        if(rezervacija.getDate().before(date)){
            rezervacija.setDiscount(rezervacija.getDiscount() + 10);
        }

        List<Day>days = new ArrayList<>();
        for (Ticket ticket: rezervacija.getTickets()
             ) {
            if(!days.contains(ticket.getDailyPlan().getDay())){
                days.add(ticket.getDailyPlan().getDay());
            }
        }
        if(days.size() == 2){
            rezervacija.setDiscount(rezervacija.getDiscount() + 10);
        }
        else if(days.size() == 3){
            rezervacija.setDiscount(rezervacija.getDiscount() + 20);
        }
    }

}
