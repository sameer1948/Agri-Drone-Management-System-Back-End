package com.agri_drone_management_system_back_end.controller;

import com.agri_drone_management_system_back_end.entity.Rate;
import com.agri_drone_management_system_back_end.service.RateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/rates")
public class RateController {

    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping
    public ResponseEntity<List<Rate>> getAllRates() {
        return ResponseEntity.ok(rateService.getAllRateHistory());
    }

    @GetMapping("/current")
    public ResponseEntity<Rate> getCurrentRate() {
        return rateService.getCurrentRate()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Rate> createNewRate(@RequestBody Rate rate) {
        Rate saved = rateService.createNewRate(rate.getPrice(), rate.getChangeReason());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}