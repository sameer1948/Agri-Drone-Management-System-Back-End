package com.agri_drone_management_system_back_end.service;

import com.agri_drone_management_system_back_end.entity.Repair;
import com.agri_drone_management_system_back_end.repository.RepairRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class RepairService {

    private final RepairRepository repairRepository;

    public RepairService(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    public List<Repair> getAllRepairs() {
        return repairRepository.findAll();
    }

    public Optional<Repair> getRepairById(Long repairId) {
        return repairRepository.findById(repairId);
    }

    public List<Repair> getRepairsByBookingId(Long bookingId) {
        return repairRepository.findByBookingId(bookingId);
    }

    public Page<Repair> getPaginatedRepairs(int page, int size, String sortBy, String sortDir, String query) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return repairRepository.searchRepairs(query, pageable);
    }

    @Transactional
    public Repair createRepair(Repair repair) {
        if (repair.getRepairDate() == null) {
            repair.setRepairDate(LocalDate.now());
        }
        if (repair.getRepairTime() == null) {
            repair.setRepairTime(LocalTime.now().withNano(0));
        }
        return repairRepository.save(repair);
    }

    @Transactional
    public Repair updateRepair(Long repairId, Repair updated) {
        return repairRepository.findById(repairId).map(existing -> {
            existing.setRepairNote(updated.getRepairNote());
            existing.setAmount(updated.getAmount());
            existing.setRepairDate(updated.getRepairDate());
            existing.setRepairTime(updated.getRepairTime());
            existing.setLocation(updated.getLocation());
            existing.setBookingId(updated.getBookingId());
            return repairRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Repair record not found with ID: " + repairId));
    }

    @Transactional
    public boolean deleteRepair(Long repairId) {
        if (repairRepository.existsById(repairId)) {
            repairRepository.deleteById(repairId);
            return true;
        }
        return false;
    }
}