package com.agri_drone_management_system_back_end.entity;

import com.agri_drone_management_system_back_end.model.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity
@Table(name = "booking")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false)
    private Long bookingId;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @Column(name = "booking_day", length = 15, nullable = false)
    private String bookingDay;

    @Column(name = "crop_name", length = 100, nullable = false)
    private String cropName;

    @Column(name = "crop_owner_name", length = 100, nullable = false)
    private String cropOwnerName;

    @Column(name = "crop_owner_phone", length = 20, nullable = false)
    private String cropOwnerPhone;

    @Column(name = "crop_size", precision = 10, scale = 2, nullable = false)
    private BigDecimal cropSize;

    @Column(name = "crop_village", length = 100, nullable = false)
    private String cropVillage;

    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "booking_notes", length = 200, nullable = true)
    private String bookingNotes;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status", length = 20, nullable = false)
    private BookingStatus bookingStatus = BookingStatus.PENDING; // default

    public Booking() {}

    public Booking(LocalDate bookingDate, String cropName, String cropOwnerName,
                   String cropOwnerPhone, BigDecimal cropSize, String cropVillage,
                   BigDecimal price, BigDecimal amount, String bookingNotes, BookingStatus bookingStatus) {
        this.bookingDate = bookingDate;
        this.cropName = cropName;
        this.cropOwnerName = cropOwnerName;
        this.cropOwnerPhone = cropOwnerPhone;
        this.cropSize = cropSize;
        this.cropVillage = cropVillage;
        this.price = price;
        this.amount = amount;
        this.bookingNotes = bookingNotes;
        this.bookingStatus = bookingStatus;
        populateDay();
    }

    @PrePersist
    @PreUpdate
    public void populateDay() {
        // 2. Ensure it is never null before saving to database
        if (this.bookingStatus == null) {
            this.bookingStatus = BookingStatus.PENDING;
        }
        // Also ensure bookingDay is populated if missing
        if (this.bookingDate != null && (this.bookingDay == null || this.bookingDay.isBlank())) {
            this.bookingDay = this.bookingDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        }
    }

}