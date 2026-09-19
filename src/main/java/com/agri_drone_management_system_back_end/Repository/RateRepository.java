package com.agri_drone_management_system_back_end.Repository;

import com.agri_drone_management_system_back_end.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
}