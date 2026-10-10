package com.agri_drone_management_system_back_end.controller;

import com.agri_drone_management_system_back_end.entity.Repair;
import com.agri_drone_management_system_back_end.service.RepairService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/repairs")
public class RepairController {

    private final RepairService repairService;

    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    // GET /v1/api/repairs
    @GetMapping
    public ResponseEntity<List<Repair>> getAllRepairs() {
        return ResponseEntity.ok(repairService.getAllRepairs());
    }

    // GET /v1/api/repairs/search?page=0&size=10&query=nozzle
    @GetMapping("/search")
    public ResponseEntity<Page<Repair>> searchRepairs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "repairId") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(required = false) String query) {
        return ResponseEntity.ok(repairService.getPaginatedRepairs(page, size, sortBy, sortDir, query));
    }

    // GET /v1/api/repairs/{repairId}
    @GetMapping("/{repairId}")
    public ResponseEntity<Repair> getRepairById(@PathVariable Long repairId) {
        return repairService.getRepairById(repairId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET /v1/api/repairs/booking/{bookingId}
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<Repair>> getRepairsByBookingId(@PathVariable Long bookingId) {
        return ResponseEntity.ok(repairService.getRepairsByBookingId(bookingId));
    }

    // POST /v1/api/repairs
    @PostMapping
    public ResponseEntity<Repair> createRepair(@RequestBody Repair repair) {
        Repair saved = repairService.createRepair(repair);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT /v1/api/repairs/{repairId}
    @PutMapping("/{repairId}")
    public ResponseEntity<Repair> updateRepair(@PathVariable Long repairId, @RequestBody Repair repair) {
        Repair updated = repairService.updateRepair(repairId, repair);
        return ResponseEntity.ok(updated);
    }

    // DELETE /v1/api/repairs/{repairId}
    @DeleteMapping("/{repairId}")
    public ResponseEntity<Void> deleteRepair(@PathVariable Long repairId) {
        if (repairService.deleteRepair(repairId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}