package com.agri_drone_management_system_back_end.service;

import com.agri_drone_management_system_back_end.Repository.RateRepository;
import com.agri_drone_management_system_back_end.entity.Rate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RateService {

    private final RateRepository rateRepository;

    public Optional<Rate> getCurrentRate() {
        return rateRepository.findAll().stream().findFirst();
    }

    public Rate setRate(BigDecimal price) {
        Rate rate = rateRepository.findAll().stream().findFirst().orElse(new Rate());
        rate.setPrice(price);
        return rateRepository.save(rate);
    }
}