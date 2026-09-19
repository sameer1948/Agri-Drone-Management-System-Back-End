package com.agri_drone_management_system_back_end.entity;

import com.agri_drone_management_system_back_end.model.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serial_no")
    private Long serialNo;

    @Column(name = "log_date", nullable = false)
    private LocalDate date;

    @Column(name = "log_time", nullable = false)
    private LocalTime time;

    @Column(name = "crop_name", length = 100, nullable = false)
    private String cropName;

    @Column(name = "crop_owner_name", length = 100, nullable = false)
    private String cropOwnerName;

    @Column(name = "crop_owner_phone", length = 20, nullable = false)
    private String cropOwnerPhone;

    @Column(name = "crop_size", nullable = false)
    private Double cropSize;

    @Column(name = "crop_village", length = 100, nullable = false)
    private String cropVillage;

    @Column(name = "tank_usage", nullable = false)
    private Double tankUsage;

    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", length = 20, nullable = false)
    private PaymentStatus paymentStatus;

}