package com.agri_drone_management_system_back_end.repository;

import com.agri_drone_management_system_back_end.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCaseAndCropIdNot(String name, Long cropId);
    List<Crop> findAllByOrderByNameAsc();

}