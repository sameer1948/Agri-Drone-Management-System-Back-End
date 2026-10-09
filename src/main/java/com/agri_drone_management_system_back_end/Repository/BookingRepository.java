package com.agri_drone_management_system_back_end.Repository;

import com.agri_drone_management_system_back_end.entity.Booking;
import com.agri_drone_management_system_back_end.model.BookingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBookingDate(LocalDate date);
    List<Booking> findByCropOwnerPhone(String phone);
    List<Booking> findByCropVillageIgnoreCase(String village);

    // Accepts BookingStatus enum, NOT String
    List<Booking> findByBookingStatus(BookingStatus bookingStatus);

    // Optional: paginated search by status if needed
    Page<Booking> findByBookingStatus(BookingStatus bookingStatus, Pageable pageable);

    @Query("SELECT b FROM Booking b WHERE " +
            "(:status IS NULL OR b.bookingStatus = :status) AND " +
            "(:cropName IS NULL OR LOWER(b.cropName) LIKE LOWER(CONCAT('%', :cropName, '%'))) AND " +
            "(:search IS NULL OR (" +
            "   LOWER(b.cropOwnerName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "   b.cropOwnerPhone LIKE CONCAT('%', :search, '%') OR " +
            "   LOWER(b.cropVillage) LIKE LOWER(CONCAT('%', :search, '%'))" +
            "))")
    Page<Booking> findWithFilters(
            @Param("status") BookingStatus status,
            @Param("cropName") String cropName,
            @Param("search") String search,
            Pageable pageable
    );

    // If you have a custom @Query, ensure the parameter matches BookingStatus
//    @Query("SELECT b FROM Booking b WHERE b.bookingStatus = :status")
//    List<Booking> filterByStatus(@Param("status") BookingStatus status);
}