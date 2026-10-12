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
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long paymentId;

    // Relational Foreign Key link to LogEntry
    /*@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_id", referencedColumnName = "log_id", nullable = false, unique = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "payment"})*/
    //private LogEntry logEntry;

    @Column(name = "log_id", nullable = false)
    private Long logId;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", length = 20, nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(name = "total_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "paid_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal paidAmount = BigDecimal.ZERO;

    @Column(name = "pending_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal pendingAmount = BigDecimal.ZERO;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "payment_time")
    private LocalTime paymentTime;

    @Column(name = "last_payment_date")
    private LocalDate lastPaymentDate;

    @Column(name = "last_payment_time")
    private LocalTime lastPaymentTime;

    @Column(name = "payment_type", length = 50)
    private String paymentType;

    @Column(name = "payment_notes", length = 10000)
    private String paymentNotes;

}