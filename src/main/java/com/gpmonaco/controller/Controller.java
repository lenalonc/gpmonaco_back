package com.gpmonaco.controller;

import com.gpmonaco.dto.AuthorizationRequest;
import com.gpmonaco.dto.CustomerDTO;
import com.gpmonaco.dto.ReservationDTO;
import com.gpmonaco.dto.ReservationPriceDTO;
import com.gpmonaco.entities.Reservation;
import com.gpmonaco.entities.Ticket;
import com.gpmonaco.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class Controller {

    private DayService dayService;
    private ZonaService zoneService;
    private CustomerService customerService;
    private ReservationService reservationService;
    private DailyPlanService dailyPlanService;

    @GetMapping("hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("jel radiiis", HttpStatus.OK);
    }

    @GetMapping("days")
    public ResponseEntity<?> getAllDays(){
        return ResponseEntity.ok(dayService.getAllDays());
    }

    @GetMapping("zones")
    public ResponseEntity<?> getAllZones(){
        return ResponseEntity.ok(zoneService.getAllZones());
    }

    @PostMapping("customer")
    public  ResponseEntity<?> createCustomer(@RequestBody CustomerDTO kupac){
        return ResponseEntity.ok(customerService.createCustomer(kupac));
    }

    @PostMapping("reservation")
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO rezervacija){
        return ResponseEntity.ok(reservationService.createReservation(rezervacija));
    }

    @GetMapping("plans")
    public ResponseEntity<?> getDailyPlans(){
        return ResponseEntity.ok(dailyPlanService.getAllDailyPlans());
    }

    @PostMapping("authorize")
    public ResponseEntity<?> authorizeAndUpdateReservation(@RequestBody AuthorizationRequest request) {
        return ResponseEntity.ok(reservationService.isAuthorizedToUpdateReservation(request.getToken(), request.getEmail()));
    }

    @PostMapping("reservation/price")
    public ResponseEntity<?> getPrice(@RequestBody ReservationPriceDTO reservationPriceDTO){
        return ResponseEntity.ok(reservationService.getEstimatePrice(reservationPriceDTO));
    }

    @PutMapping("reservation/{id}")
    public ResponseEntity<?> updateReservation(@RequestBody Reservation reservation, @PathVariable Long id){
        return ResponseEntity.ok(reservationService.updateReservation(id, reservation));
    }


    @DeleteMapping("reservation/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
       reservationService.deleteReservation(id);
       return ResponseEntity.ok().build();
    }

    @GetMapping("days/minprice")
    public ResponseEntity<?> getMinPriceDays(){
    return ResponseEntity.ok(dailyPlanService.getMinPrice());
    }

}
