package com.gpmonaco.service;

import com.gpmonaco.entities.PromoCode;
import com.gpmonaco.exceptions.BadRequestException;
import com.gpmonaco.repository.PromoCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PromoCodeServiceImpl implements PromoCodeService{

    @Autowired
    private PromoCodeRepository promoCodeRepository;
    @Override
    public boolean checkPromoCode(PromoCode promoCode) {
        if(promoCode != null){
           Optional<PromoCode> promo = promoCodeRepository.findByCode(promoCode.getCode());
           if(promo.isPresent()){
               if(promo.get().isActive()){
                   return true;
               }
           }
       }
       return false;
    }
}
