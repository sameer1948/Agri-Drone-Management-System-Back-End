package com.agri_drone_management_system_back_end.repository;

import com.agri_drone_management_system_back_end.entity.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VillageRepository extends JpaRepository<Village, Long> {

    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCaseAndVillageIdNot(String name, Long villageId);
    List<Village> findAllByOrderByNameAsc();

}