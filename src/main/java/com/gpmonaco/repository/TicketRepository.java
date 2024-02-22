package com.gpmonaco.repository;

import com.gpmonaco.entities.DailyPlan;
import com.gpmonaco.entities.Reservation;
import com.gpmonaco.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    //List<Ticket> findByDailyPlanAndReservation(DailyPlan dailyPlan, Reservation reservation);

}
