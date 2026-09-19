package com.agri_drone_management_system_back_end.service;

import com.agri_drone_management_system_back_end.Repository.LogRepository;
import com.agri_drone_management_system_back_end.entity.LogEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LogService {

    private final LogRepository logRepository;

    public List<LogEntry> getAllLogs() {
        return logRepository.findAll();
    }

    public Optional<LogEntry> getLogBySerialNo(Long serialNo) {
        return logRepository.findById(serialNo);
    }

    public LogEntry createLog(LogEntry logEntry) {
        return logRepository.save(logEntry);
    }

    public Optional<LogEntry> updateLog(Long serialNo, LogEntry updated) {
        return logRepository.findById(serialNo).map(existing -> {
            existing.setDate(updated.getDate());
            existing.setTime(updated.getTime());
            existing.setCropName(updated.getCropName());
            existing.setCropOwnerName(updated.getCropOwnerName());
            existing.setCropOwnerPhone(updated.getCropOwnerPhone());
            existing.setCropSize(updated.getCropSize());
            existing.setCropVillage(updated.getCropVillage());
            existing.setTankUsage(updated.getTankUsage());
            existing.setAmount(updated.getAmount());
            existing.setPaymentStatus(updated.getPaymentStatus());
            return logRepository.save(existing);
        });
    }

    public boolean deleteLog(Long serialNo) {
        if (logRepository.existsById(serialNo)) {
            logRepository.deleteById(serialNo);
            return true;
        }
        return false;
    }
}