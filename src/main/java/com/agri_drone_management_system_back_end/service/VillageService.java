package com.agri_drone_management_system_back_end.service;

import com.agri_drone_management_system_back_end.entity.Village;
import com.agri_drone_management_system_back_end.repository.VillageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VillageService {

    private final VillageRepository villageRepository;

    public List<Village> getAllVillages() {
        return villageRepository.findAllByOrderByNameAsc();
    }

    @Transactional
    public Village addVillage(Village village) {
        String trimmedName = village.getName() != null ? village.getName().trim() : "";
        if (trimmedName.isEmpty()) {
            throw new IllegalArgumentException("Village name cannot be empty.");
        }
        if (villageRepository.existsByNameIgnoreCase(trimmedName)) {
            throw new IllegalArgumentException("Village '" + trimmedName + "' already exists.");
        }
        village.setName(trimmedName);
        return villageRepository.save(village);
    }

    @Transactional
    public Village updateVillage(Long id, Village updated) {
        String trimmedName = updated.getName() != null ? updated.getName().trim() : "";
        if (trimmedName.isEmpty()) {
            throw new IllegalArgumentException("Village name cannot be empty.");
        }

        Village existing = villageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Village not found with id: " + id));

        if (villageRepository.existsByNameIgnoreCaseAndVillageIdNot(trimmedName, id)) {
            throw new IllegalArgumentException("Village '" + trimmedName + "' already exists.");
        }

        existing.setName(trimmedName);
        return villageRepository.save(existing);
    }

    @Transactional
    public void deleteVillage(Long id) {
        villageRepository.deleteById(id);
    }

}