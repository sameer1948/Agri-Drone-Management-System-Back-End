package com.agri_drone_management_system_back_end.repository;

import com.agri_drone_management_system_back_end.entity.MaxCapacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaxCapacityRepository extends JpaRepository<MaxCapacity, Long> {

}