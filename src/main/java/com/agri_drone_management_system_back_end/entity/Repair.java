package com.agri_drone_management_system_back_end.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "repairs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repair_id", nullable = false)
    private Long repairId;

    @Column(name = "repair_note", length = 500, nullable = false)
    private String repairNote;

    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(name = "repair_date", nullable = false)
    private LocalDate repairDate;

    @Column(name = "repair_time", nullable = false)
    private LocalTime repairTime;

    @Column(name = "location", length = 150, nullable = false)
    private String location;

    @Column(name = "booking_id")
    private Long bookingId;

}