package com.agri_drone_management_system_back_end.service;

import com.agri_drone_management_system_back_end.repository.DroneRepository;
import com.agri_drone_management_system_back_end.entity.Drone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DroneService {

    private final DroneRepository droneRepository;

    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    public Optional<Drone> getDroneById(String droneId) {
        return droneRepository.findById(droneId);
    }

    public Drone createDrone(Drone drone) {
        return droneRepository.save(drone);
    }

    public Optional<Drone> updateDrone(String droneId, Drone updatedDrone) {
        return droneRepository.findById(droneId).map(existingDrone -> {
            existingDrone.setDroneName(updatedDrone.getDroneName());
            existingDrone.setDroneOwnerName(updatedDrone.getDroneOwnerName());
            existingDrone.setDroneOwnerPhone(updatedDrone.getDroneOwnerPhone());
            existingDrone.setDroneStatus(updatedDrone.getDroneStatus());
            return droneRepository.save(existingDrone);
        });
    }

    public boolean deleteDrone(String droneId) {
        if (droneRepository.existsById(droneId)) {
            droneRepository.deleteById(droneId);
            return true;
        }
        return false;
    }
}