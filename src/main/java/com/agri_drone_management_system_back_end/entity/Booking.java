package com.agri_drone_management_system_back_end.entity;

import com.agri_drone_management_system_back_end.model.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false)
    private Long bookingId;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @Column(name = "booking_time", nullable = false)
    private LocalTime bookingTime;

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

    @Column(name = "booking_notes", length = 1000)
    private String bookingNotes;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status", length = 20, nullable = false)
    private BookingStatus bookingStatus = BookingStatus.PENDING; // default

    @PrePersist
    public void onPrePersist() {
        if (this.bookingTime == null) {
            this.bookingTime = LocalTime.now();
        }
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