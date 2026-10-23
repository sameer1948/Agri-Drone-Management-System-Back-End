package com.agri_drone_management_system_back_end.controller;

import com.agri_drone_management_system_back_end.entity.Rate;
import com.agri_drone_management_system_back_end.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RequestMapping("/v1/api/rates")
@RequiredArgsConstructor
@RestController
public class RateController {

    private final RateService rateService;

    @GetMapping
    public ResponseEntity<List<Rate>> getAllRates() {
        return ResponseEntity.ok(rateService.getAllRates());
    }

    @GetMapping("/current")
    public ResponseEntity<Rate> getCurrentRate() {
        return ResponseEntity.ok(rateService.getCurrentRate());
    }

    @PostMapping
    public ResponseEntity<Rate> addRate(@RequestBody Rate rate) {
        return ResponseEntity.ok(rateService.addRate(rate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rate> updateRate(@PathVariable Long id, @RequestBody Rate rate) {
        return ResponseEntity.ok(rateService.updateRate(id, rate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRate(@PathVariable Long id) {
        rateService.deleteRate(id);
        return ResponseEntity.noContent().build();
    }

}