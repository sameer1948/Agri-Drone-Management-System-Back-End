package com.agri_drone_management_system_back_end.controller;

import com.agri_drone_management_system_back_end.entity.Drone;
import com.agri_drone_management_system_back_end.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/drones")
@RequiredArgsConstructor
public class DroneController {

    private final DroneService droneService;

    // 1. GET ALL: GET /api/drones
    @GetMapping
    public ResponseEntity<List<Drone>> getAllDrones() {
        List<Drone> drones = droneService.getAllDrones();
        return ResponseEntity.ok(drones);
    }

    // 2. GET ONE: GET /api/drones/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Drone> getDroneById(@PathVariable("id") String droneId) {
        return droneService.getDroneById(droneId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 3. CREATE: POST /api/drones
    @PostMapping
    public ResponseEntity<Drone> createDrone(@RequestBody Drone drone) {
        Drone created = droneService.createDrone(drone);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // 4. UPDATE: PUT /api/drones/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Drone> updateDrone(
            @PathVariable("id") String droneId,
            @RequestBody Drone droneDetails) {
        return droneService.updateDrone(droneId, droneDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 5. DELETE: DELETE /api/drones/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrone(@PathVariable("id") String droneId) {
        boolean deleted = droneService.deleteDrone(droneId);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
    }
}