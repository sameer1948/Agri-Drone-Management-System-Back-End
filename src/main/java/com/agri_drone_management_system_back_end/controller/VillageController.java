package com.agri_drone_management_system_back_end.controller;

import com.agri_drone_management_system_back_end.entity.Village;
import com.agri_drone_management_system_back_end.service.VillageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/villages")
@RequiredArgsConstructor
public class VillageController {

    private final VillageService villageService;

    @GetMapping
    public ResponseEntity<List<Village>> getAll() {
        return ResponseEntity.ok(villageService.getAllVillages());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Village village) {
        try {
            Village saved = villageService.addVillage(village);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Village village) {
        try {
            Village updated = villageService.updateVillage(id, village);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        villageService.deleteVillage(id);
        return ResponseEntity.noContent().build();
    }

}