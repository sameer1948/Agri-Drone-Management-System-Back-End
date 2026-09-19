package com.agri_drone_management_system_back_end.Repository;

import com.agri_drone_management_system_back_end.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {

    List<Drone> findByDroneOwnerNameContainingIgnoreCase(String ownerName);

}