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

    @GetMapping("/{serialNo}")
    public ResponseEntity<LogEntry> getLogById(@PathVariable Long serialNo) {
        return logService.getLogBySerialNo(serialNo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LogEntry> createLog(@RequestBody LogEntry logEntry) {
        LogEntry created = logService.createLog(logEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{serialNo}")
    public ResponseEntity<LogEntry> updateLog(@PathVariable Long serialNo, @RequestBody LogEntry updated) {
        return logService.updateLog(serialNo, updated)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{serialNo}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long serialNo) {
        if (logService.deleteLog(serialNo)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}