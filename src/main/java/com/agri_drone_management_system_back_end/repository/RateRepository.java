package com.agri_drone_management_system_back_end.repository;

import com.agri_drone_management_system_back_end.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

    List<Rate> findAllByOrderByCreatedAtDesc();
    Optional<Rate> findFirstByOrderByCreatedAtDesc();

}