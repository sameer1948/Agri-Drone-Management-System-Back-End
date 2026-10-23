package com.agri_drone_management_system_back_end.service;

import com.agri_drone_management_system_back_end.entity.Rate;
import com.agri_drone_management_system_back_end.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RateService {

    private final RateRepository rateRepository;

    @Transactional(readOnly = true)
    public List<Rate> getAllRates() {
        return rateRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public Rate getCurrentRate() {
        return rateRepository.findFirstByOrderByCreatedAtDesc()
                .orElseGet(() -> rateRepository.save(Rate.builder().price(BigDecimal.valueOf(250.00)).build()));
    }

    @Transactional
    public Rate addRate(Rate rate) {
        return rateRepository.save(rate);
    }

    @Transactional
    public Rate updateRate(Long id, Rate rate) {
        Rate existing = rateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rate not found with ID: " + id));
        existing.setPrice(rate.getPrice());
        return rateRepository.save(existing);
    }

    @Transactional
    public void deleteRate(Long id) {
        rateRepository.deleteById(id);
    }

}