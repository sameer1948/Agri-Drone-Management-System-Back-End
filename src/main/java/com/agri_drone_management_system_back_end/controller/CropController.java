package com.agri_drone_management_system_back_end.controller;

import com.agri_drone_management_system_back_end.entity.Crop;
import com.agri_drone_management_system_back_end.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/crops")
@RequiredArgsConstructor
public class CropController {

    private final CropService cropService;

    @GetMapping
    public ResponseEntity<List<Crop>> getAll() {
        return ResponseEntity.ok(cropService.getAllCrops());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Crop crop) {
        try {
            Crop saved = cropService.addCrop(crop);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Crop crop) {
        try {
            Crop updated = cropService.updateCrop(id, crop);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cropService.deleteCrop(id);
        return ResponseEntity.noContent().build();
    }

}