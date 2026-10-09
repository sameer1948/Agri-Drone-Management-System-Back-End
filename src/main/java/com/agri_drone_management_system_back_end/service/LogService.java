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

    public Optional<LogEntry> getLogByLogId(Long logId) {
        return logRepository.findById(logId);
    }

    public LogEntry createLog(LogEntry logEntry) {
        return logRepository.save(logEntry);
    }

    public Optional<LogEntry> updateLog(Long logId, LogEntry updated) {
        return logRepository.findById(logId).map(existing -> {
            existing.setBookingId(updated.getBookingId());
            existing.setDroneId(updated.getDroneId());
            existing.setCreateDate(updated.getCreateDate());
            existing.setCreateTime(updated.getCreateTime());
            existing.setCropName(updated.getCropName());
            existing.setCropOwnerName(updated.getCropOwnerName());
            existing.setCropOwnerPhone(updated.getCropOwnerPhone());
            existing.setCropSize(updated.getCropSize());
            existing.setCropVillage(updated.getCropVillage());
            existing.setTankUsage(updated.getTankUsage());
            existing.setAmount(updated.getAmount());
            existing.setCompleteDate(updated.getCompleteDate());
            existing.setCompleteTime(updated.getCompleteTime());
            existing.setActivityStatus(updated.getActivityStatus());
            existing.setPaymentStatus(updated.getPaymentStatus());
            return logRepository.save(existing);
        });
    }

    public boolean deleteLog(Long logId) {
        if (logRepository.existsById(logId)) {
            logRepository.deleteById(logId);
            return true;
        }
        return false;
    }
}