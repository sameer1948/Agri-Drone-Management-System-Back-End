package com.agri_drone_management_system_back_end.service;

import com.agri_drone_management_system_back_end.repository.BookingRepository;
import com.agri_drone_management_system_back_end.entity.Booking;
import com.agri_drone_management_system_back_end.model.BookingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId);
    }

    public List<Booking> getBookingByStatus(BookingStatus status) {
        List<Booking> bookings = bookingRepository.findByBookingStatus(status);
        if(bookings.isEmpty()){
            return List.of();
        }
        return bookings;
    }

    public Page<Booking> getBookingsWithFilters(BookingStatus status, String cropName, String search, Pageable pageable) {
        return bookingRepository.findWithFilters(status, cropName, search, pageable);
    }

    public Page<Booking> getPaginatedBookings(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return bookingRepository.findAll(pageable);
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Optional<Booking> updateBooking(Long bookingId, Booking updated) {
        System.out.println("Updating booking with ID: " + updated.toString());
        return bookingRepository.findById(bookingId).map(existing -> {
            existing.setBookingDate(updated.getBookingDate());
            existing.setBookingTime(updated.getBookingTime());
            existing.setCropName(updated.getCropName());
            existing.setCropOwnerName(updated.getCropOwnerName());
            existing.setCropOwnerPhone(updated.getCropOwnerPhone());
            existing.setCropSize(updated.getCropSize());
            existing.setCropVillage(updated.getCropVillage());
            existing.setBookingNotes(updated.getBookingNotes());
            existing.setBookingStatus(updated.getBookingStatus());
            return bookingRepository.save(existing);
        });
    }

    public boolean deleteBooking(Long bookingId) {
        if (bookingRepository.existsById(bookingId)) {
            bookingRepository.deleteById(bookingId);
            return true;
        }
        return false;
    }
}