package com.agri_drone_management_system_back_end.Repository;

import com.agri_drone_management_system_back_end.entity.LogEntry;
import com.agri_drone_management_system_back_end.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<LogEntry, Long> {
    List<LogEntry> findByPaymentStatus(PaymentStatus paymentStatus);
    List<LogEntry> findByCropVillageIgnoreCase(String village);
}