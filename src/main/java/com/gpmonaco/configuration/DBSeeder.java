package com.gpmonaco.configuration;

import com.gpmonaco.dto.ReservationDTO;
import com.gpmonaco.dto.TicketDTO;
import com.gpmonaco.entities.*;
import com.gpmonaco.repository.CustomerRepository;
import com.gpmonaco.repository.DayRepository;
import com.gpmonaco.repository.DailyPlanRepository;
import com.gpmonaco.repository.ZonaRepository;
import com.gpmonaco.service.ReservationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class DBSeeder implements CommandLineRunner {

    ZonaRepository zoneRepository;
    DayRepository dayRepository;
    DailyPlanRepository dailyPlanRepository;
    ReservationService reservationService;
    CustomerRepository customerRepository;
    ModelMapper mapper;


    private void seedDnevniPlan(){
    List<Day> dani = dayRepository.findAll();
    List<Zone> zone = zoneRepository.findAll();
    for (int i = 0; i < dani.size(); i++){
        for (int j = 0; j < zone.size(); j++) {
            DailyPlan dnevniPlan = DailyPlan.builder()
                    .day(dani.get(i))
                    .zone(zone.get(j))
                    .capacity(zone.get(j).getFeatures().getCapacity())
                    .build();
            dailyPlanRepository.save(dnevniPlan);
            }
        }
    }

    private void seedRezervacija(){
        Reservation reservation = new Reservation();
        List<DailyPlan> dp = dailyPlanRepository.findAll();
        Customer c = customerRepository.findAll().get(0);
        c.setReservations(new ArrayList<Reservation>());
        reservation.setCustomer(c);
        Ticket ticket = new Ticket();
        ticket.setReservation(reservation);
        ticket.setDailyPlan(dp.get(0));
        Ticket ticket2 = new Ticket();
        ticket2.setReservation(reservation);
        ticket2.setDailyPlan(dp.get(6));
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        tickets.add(ticket2);
        reservation.setTickets(tickets);

    reservationService.createReservation(reservation);
    }


    @Override
    public void run(String... args) throws Exception {
        //seedDnevniPlan();
        seedRezervacija();
    }
}
