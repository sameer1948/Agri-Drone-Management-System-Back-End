package com.agri_drone_management_system_back_end.entity;

import com.agri_drone_management_system_back_end.model.ActivityStatus;
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
    @Column(name = "log_id", nullable = false)
    private Long logId;

    @Column(name = "booking_id", nullable = false)
    private Long bookingId;

    @Column(name = "drone_id", length = 50, nullable = false)
    private String droneId;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @Column(name = "create_time", nullable = false)
    private LocalTime createTime;

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

    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "confirmed_date")
    private LocalDate confirmedDate;

    @Column(name = "confirmed_time")
    private LocalTime confirmedTime;

    @Column(name = "complete_date")
    private LocalDate completeDate;

    @Column(name = "complete_time")
    private LocalTime completeTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_status", length = 20, nullable = false)
    private ActivityStatus activityStatus = ActivityStatus.CONFIRMED; // default

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", length = 20, nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING; // default

}