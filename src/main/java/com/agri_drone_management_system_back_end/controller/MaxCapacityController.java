package com.agri_drone_management_system_back_end.controller;

import com.agri_drone_management_system_back_end.entity.MaxCapacity;
import com.agri_drone_management_system_back_end.service.MaxCapacityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/capacity")
@RequiredArgsConstructor
public class MaxCapacityController {

    private final MaxCapacityService maxCapacityService;

    @GetMapping
    public ResponseEntity<MaxCapacity> getMaxCapacity() {
        return ResponseEntity.ok(maxCapacityService.getCapacity());
    }

    @PutMapping
    public ResponseEntity<MaxCapacity> updateMaxCapacity(@RequestBody MaxCapacity request) {
        if (request.getMaxAcres() == null || request.getMaxAcres() <= 0) {
            return ResponseEntity.badRequest().build();
        }

        MaxCapacity updated = maxCapacityService.updateCapacity(request.getMaxAcres());
        return ResponseEntity.ok(updated);
    }

}