package com.gpmonaco.service;

import com.gpmonaco.dto.*;
import com.gpmonaco.entities.*;
import com.gpmonaco.exceptions.BadRequestException;
import com.gpmonaco.exceptions.ForbiddenException;
import com.gpmonaco.exceptions.NotFoundException;
import com.gpmonaco.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.JwtUtil;
import util.PromoCodeUtil;

import java.util.*;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DailyPlanRepository dailyPlanRepository;

    @Autowired
    PromoCodeRepository promoCodeRepository;

    @Autowired
    ModelMapper mapper;
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public List<ReservationDTO> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().map(r -> mapper.map(r, ReservationDTO.class)).toList();
    }

    @Transactional
    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = mapper.map(reservationDTO, Reservation.class);
        Customer customer = customerRepository.save(reservation.getCustomer());

        reservation.setCustomer(customer);
        List<Ticket> karte = reservation.getTickets();
        validateTickets(karte);
        reservation.setDate(new Date());
        reservation.setPrice(getSumPrice(karte));
        String token = jwtUtil.generateToken(reservation);
        reservation.setToken(token);



        checkDiscount(reservation);
        if(reservation.getDiscount() != 0){
            reservation.setPrice(reservation.getPrice() * (100 - reservation.getDiscount())/100);
        }

        PromoCode promoCode = PromoCode.builder().code(PromoCodeUtil.generateUniquePromoCode(customer.getEmail(), 8)).active(true).build();
        promoCode = promoCodeRepository.save(promoCode);
        reservation.setPromoCode(promoCode);

        Reservation finalReservation = reservationRepository.save(reservation);
        karte.forEach(karta -> karta.setReservation(finalReservation));
        ticketRepository.saveAll(karte);
        for (Ticket karta: karte
             ) {
            Optional<DailyPlan> plan = dailyPlanRepository.findById(karta.getDailyPlan().getId());
            plan.get().reduceCapacity(karta.getQuantity());
            dailyPlanRepository.save(plan.get());
        }
        return mapper.map(finalReservation, ReservationDTO.class);
    }



    @Override
    public ReservationDTO isAuthorizedToUpdateReservation(String token, String email) {
        if(!jwtUtil.isTokenActive(token)) throw new ForbiddenException("This token is inactive");
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Invalid data"));
        Reservation reservation = reservationRepository.findByTokenAndCustomer(token, customer).orElseThrow(
                () -> new NotFoundException("Invalid data"));
        return mapper.map(reservation, ReservationDTO.class);
    }

    @Override
    public void deleteReservation(Long id) {
        Reservation r = reservationRepository.findById(id).orElseThrow(()->new NotFoundException("Reservation cannot be found"));
        reservationRepository.deleteById(id);
        jwtUtil.makeTokenPassive(r.getToken());
    }

    @Override
    public ReservationPriceDTO getEstimatePrice(ReservationPriceDTO reservationPriceDTO) {
        List<Ticket> karte = reservationPriceDTO.getTickets().stream()
                .map(ticketDTO -> mapper.map(ticketDTO, Ticket.class))
                .toList();

        reservationPriceDTO.setPrice(getSumPrice(karte));
        checkDiscountDTO(reservationPriceDTO, karte);

        if(reservationPriceDTO.getDiscount() != 0){
            reservationPriceDTO.setPrice(reservationPriceDTO.getPrice() * (100 - reservationPriceDTO.getDiscount())/100);
        }

        return reservationPriceDTO;
    }

    @Override
    public ReservationDTO updateReservation(Long id, Reservation reservation) {
        Reservation orgReservation = reservationRepository.findById(id).orElseThrow(()-> new NotFoundException("Reservation not found"));

        reservation.setId(orgReservation.getId());

        List<Ticket> newTickets = reservation.getTickets();
        List<Ticket> orgTickets = orgReservation.getTickets();

        for (Ticket orgTicket: orgTickets) {
            Ticket t = newTickets.stream()
                    .filter(ticket -> Objects.equals(ticket.getId(), orgTicket.getId()))
                    .findFirst()
                    .orElse(null);

            if(t != null) {
                if(t.getQuantity() > orgTicket.getQuantity()) {
                    t.getDailyPlan().reduceCapacity(t.getQuantity() - orgTicket.getQuantity());
                    dailyPlanRepository.save(t.getDailyPlan());

                    t.setReservation(reservation);
                    ticketRepository.save(t);

                }else if(t.getQuantity() < orgTicket.getQuantity()) {
                    t.getDailyPlan().addCapacity(orgTicket.getQuantity() - t.getQuantity());
                    dailyPlanRepository.save(t.getDailyPlan());

                    t.setReservation(reservation);
                    ticketRepository.save(t);
                }
            }else {
                orgTicket.getDailyPlan().addCapacity(orgTicket.getQuantity());
                dailyPlanRepository.save(orgTicket.getDailyPlan());

                ticketRepository.deleteById(orgTicket.getId());
            }
        }

        for (Ticket newTicket: newTickets) {
            Ticket orgT = orgTickets.stream()
                    .filter(orgTicket -> orgTicket.getId() == newTicket.getId()) // Change this condition according to your requirements
                    .findFirst()
                    .orElse(null);
            if(orgT == null) {
                newTicket.getDailyPlan().reduceCapacity(newTicket.getQuantity());
                dailyPlanRepository.save(newTicket.getDailyPlan());

                newTicket.setReservation(reservation);
                ticketRepository.save(newTicket);
            }
        }

            double price = getSumPrice(newTickets);
            reservation.setPrice(price);

            checkDiscount(reservation);
            if (reservation.getDiscount() != 0) {
                reservation.setPrice(reservation.getPrice() * (100 - reservation.getDiscount()) / 100);
            }

            reservation.setPromoCode(orgReservation.getPromoCode());

            if(reservation.getPromoCode() != null){
                reservation.setDiscount(reservation.getDiscount() + 5);
            }

            reservation.setCustomer(orgReservation.getCustomer());
            reservation.setToken(orgReservation.getToken());
            reservation.setDate(orgReservation.getDate());

            Reservation finalReservation = reservationRepository.save(reservation);

            return mapper.map(finalReservation, ReservationDTO.class);
        }

    private double getSumPrice(List<Ticket> karte) {
        return karte.stream()
                .mapToDouble(karta -> karta.getDailyPlan().getZone().getFeatures().getPrice() * karta.getQuantity())
                .sum();
    }

    private void validateTickets(List<Ticket> karte) {
        for (Ticket karta: karte
             ) {
            if(karta.getDailyPlan().getCapacity() == 0){
                throw new ForbiddenException("Tickets in zone "+
                        karta.getDailyPlan().getZone().getFeatures().getName()+
                        " on "+karta.getDailyPlan().getDay().getDate() + "are sold out.");
            }
        }
    }

    private void checkDiscount(Reservation rezervacija) {
        Date date = new Date(124, 2, 16);
        rezervacija.setDiscount(0.0);
        if(rezervacija.getDate().before(date)){
            rezervacija.setDiscount(rezervacija.getDiscount() + 10);
        }

        if(rezervacija.getPromoCode()!=null && rezervacija.getPromoCode().getCode() != ""){
           Optional<PromoCode> promo = promoCodeRepository.findByCode(rezervacija.getPromoCode().getCode());
           if(promo == null){
               throw new BadRequestException("Promo code doesn't exist");
           }
           if(promo.get().isActive()) {
               rezervacija.setDiscount(rezervacija.getDiscount() + 5);
               promo.get().setActive(false);
               promoCodeRepository.save(promo.get());
           }
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

    private void checkDiscountDTO(ReservationPriceDTO rezervacija, List<Ticket> karte){
        Date date = new Date(124, 2, 16);
        rezervacija.setDiscount(0.0);
        if(new Date().before(date)){
            rezervacija.setDiscount(rezervacija.getDiscount() + 10);
        }

        List<Day>days = new ArrayList<>();
        for (Ticket ticket: karte
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
