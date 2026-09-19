package com.agri_drone_management_system_back_end.controller;

import com.agri_drone_management_system_back_end.entity.Rate;
import com.agri_drone_management_system_back_end.service.RateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/rates")
public class RateController {

    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping
    public ResponseEntity<Rate> getRate() {
        return rateService.getCurrentRate()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Rate> updateRate(@RequestBody Map<String, BigDecimal> request) {
        BigDecimal price = request.get("price");
        if (price == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(rateService.setRate(price));
    }

}