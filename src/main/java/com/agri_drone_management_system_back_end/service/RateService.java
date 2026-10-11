package com.agri_drone_management_system_back_end.service;

import com.agri_drone_management_system_back_end.repository.RateRepository;
import com.agri_drone_management_system_back_end.entity.Rate;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RateService {

    private final RateRepository rateRepository;

    public List<Rate> getAllRateHistory() {
        return rateRepository.findAll();
    }

    public Optional<Rate> getCurrentRate() {
        return rateRepository.findByIsActiveTrue();
    }

    @Transactional
    public Rate createNewRate(BigDecimal newPrice, String reason) {
        // Deactivate old rate and close date window
        rateRepository.findByIsActiveTrue().ifPresent(oldRate -> {
            oldRate.setIsActive(false);
            oldRate.setEffectiveTo(LocalDateTime.now());
            rateRepository.save(oldRate);
        });

        // Insert new active rate
        Rate rate = new Rate();
        rate.setPrice(newPrice);
        rate.setIsActive(true);
        rate.setEffectiveFrom(LocalDateTime.now());
        rate.setChangeReason(reason);
        rate.setCreatedAt(LocalDateTime.now());
        return rateRepository.save(rate);
    }

}