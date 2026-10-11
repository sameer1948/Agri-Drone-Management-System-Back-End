package com.agri_drone_management_system_back_end.service;

import com.agri_drone_management_system_back_end.entity.Crop;
import com.agri_drone_management_system_back_end.repository.CropRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CropService {

    private final CropRepository cropRepository;

    public List<Crop> getAllCrops() {
        return cropRepository.findAllByOrderByNameAsc();
    }

    @Transactional
    public Crop addCrop(Crop crop) {
        String trimmedName = crop.getName() != null ? crop.getName().trim() : "";
        if (trimmedName.isEmpty()) {
            throw new IllegalArgumentException("Crop name cannot be empty.");
        }
        if (cropRepository.existsByNameIgnoreCase(trimmedName)) {
            throw new IllegalArgumentException("Crop '" + trimmedName + "' already exists.");
        }
        crop.setName(trimmedName);
        return cropRepository.save(crop);
    }

    @Transactional
    public Crop updateCrop(Long id, Crop updated) {
        String trimmedName = updated.getName() != null ? updated.getName().trim() : "";
        if (trimmedName.isEmpty()) {
            throw new IllegalArgumentException("Crop name cannot be empty.");
        }

        Crop existing = cropRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Crop not found with id: " + id));

        if (cropRepository.existsByNameIgnoreCaseAndCropIdNot(trimmedName, id)) {
            throw new IllegalArgumentException("Crop '" + trimmedName + "' already exists.");
        }

        existing.setName(trimmedName);
        return cropRepository.save(existing);
    }

    @Transactional
    public void deleteCrop(Long id) {
        cropRepository.deleteById(id);
    }

}