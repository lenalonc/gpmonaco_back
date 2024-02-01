package com.gpmonaco.controller;

import com.gpmonaco.dto.CustomerDTO;
import com.gpmonaco.dto.ReservationDTO;
import com.gpmonaco.entities.Reservation;
import com.gpmonaco.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class Controller {

    private DayService danService;
    private ZonaService zonaService;
    private CustomerService kupacService;
    private ReservationService rezervacijaService;
    private DailyPlanService dailyPlanService;

    @GetMapping("hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("jel radiiis", HttpStatus.OK);
    }

    @GetMapping("dani")
    public ResponseEntity<?> getAllDays(){
        return ResponseEntity.ok(danService.getAllDays());
    }

    @GetMapping("zone")
    public ResponseEntity<?> getAllZones(){
        return ResponseEntity.ok(zonaService.getAllZones());
    }

    @PostMapping("kupac")
    public  ResponseEntity<?> createCustomer(@RequestBody CustomerDTO kupac){
        return ResponseEntity.ok(kupacService.createCustomer(kupac));
    }

    @PostMapping("rezervacija")
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO rezervacija){
        return ResponseEntity.ok(rezervacijaService.createReservation(rezervacija));
    }

    @GetMapping("planovi")
    public ResponseEntity<?> getDailyPlans(){
        return ResponseEntity.ok(dailyPlanService.getAllDailyPlans());
    }

}
