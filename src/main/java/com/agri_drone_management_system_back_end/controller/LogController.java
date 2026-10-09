package com.agri_drone_management_system_back_end.controller;

import com.agri_drone_management_system_back_end.entity.LogEntry;
import com.agri_drone_management_system_back_end.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/logs")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public ResponseEntity<List<LogEntry>> getAllLogs() {
        return ResponseEntity.ok(logService.getAllLogs());
    }

    @GetMapping("/{logId}")
    public ResponseEntity<LogEntry> getLogById(@PathVariable Long logId) {
        return logService.getLogByLogId(logId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LogEntry> createLog(@RequestBody LogEntry logEntry) {
        LogEntry created = logService.createLog(logEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{logId}")
    public ResponseEntity<LogEntry> updateLog(@PathVariable Long logId, @RequestBody LogEntry updated) {
        return logService.updateLog(logId, updated)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{logId}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long logId) {
        if (logService.deleteLog(logId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Inside LogController.java:

 /*   @PatchMapping("/{serialNo}/payment-status")
    public ResponseEntity<LogEntry> updatePaymentStatus(
            @PathVariable Long serialNo,
            @RequestParam PaymentStatus status) {

        return logRepository.findById(serialNo).map(log -> {
            log.setPaymentStatus(status);
            LogEntry updated = logRepository.save(log);
            return ResponseEntity.ok(updated);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }*/
}