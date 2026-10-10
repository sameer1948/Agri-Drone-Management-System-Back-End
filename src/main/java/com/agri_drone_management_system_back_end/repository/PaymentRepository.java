package com.agri_drone_management_system_back_end.repository;

import com.agri_drone_management_system_back_end.entity.Payment;
import com.agri_drone_management_system_back_end.model.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByLogId(Long logId);

    // Filter by payment status (PENDING, PAID, etc.)
    List<Payment> findByPaymentStatus(PaymentStatus paymentStatus);

    Page<Payment> findByPaymentStatus(PaymentStatus paymentStatus, Pageable pageable);

    // Filter by initial creation date
    List<Payment> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);

    // Search query with optional filters
    @Query("SELECT p FROM Payment p WHERE " +
            "(:status IS NULL OR p.paymentStatus = :status) AND " +
            "(:paymentType IS NULL OR LOWER(p.paymentType) = LOWER(:paymentType))")
    Page<Payment> findWithFilters(@Param("status") PaymentStatus status,
                                  @Param("paymentType") String paymentType,
                                  Pageable pageable);
}