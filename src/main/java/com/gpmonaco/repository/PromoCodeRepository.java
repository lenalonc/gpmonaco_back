package com.gpmonaco.repository;

import com.gpmonaco.entities.Customer;
import com.gpmonaco.entities.PromoCode;
import com.gpmonaco.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {

    Optional<PromoCode> findByCode(String code);
}
