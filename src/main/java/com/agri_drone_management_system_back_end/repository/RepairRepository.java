package com.agri_drone_management_system_back_end.repository;

import com.agri_drone_management_system_back_end.entity.Repair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {

    // Find repairs associated with a specific booking
    List<Repair> findByBookingId(Long bookingId);

    // Filter by place or search keyword
    @Query("SELECT r FROM Repair r WHERE " +
            "(:query IS NULL OR LOWER(r.repairNote) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(r.place) LIKE LOWER(CONCAT('%', :query, '%')))")
    Page<Repair> searchRepairs(@Param("query") String query, Pageable pageable);
}