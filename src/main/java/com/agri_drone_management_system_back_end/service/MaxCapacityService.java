package com.agri_drone_management_system_back_end.service;

import com.agri_drone_management_system_back_end.entity.MaxCapacity;
import com.agri_drone_management_system_back_end.repository.MaxCapacityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MaxCapacityService {

    private final MaxCapacityRepository maxCapacityRepository;

    @Transactional
    public MaxCapacity getCapacity() {
        return maxCapacityRepository.findById(1L)
                .orElseGet(() -> {
                    MaxCapacity defaultCap = new MaxCapacity();
                    defaultCap.setId(1L);
                    defaultCap.setMaxAcres(30);
                    return maxCapacityRepository.save(defaultCap);
                });
    }

    @Transactional
    public MaxCapacity updateCapacity(Integer maxAcres) {
        MaxCapacity capacity = maxCapacityRepository.findById(1L)
                .orElseGet(() -> {
                    MaxCapacity newCap = new MaxCapacity();
                    newCap.setId(1L);
                    return newCap;
                });

        capacity.setMaxAcres(maxAcres);
        // Inside a @Transactional method, modifying the managed entity automatically flushes changes.
        // Returning save() is safe here because 'capacity' is fully managed in this session.
        return maxCapacityRepository.save(capacity);
    }

}