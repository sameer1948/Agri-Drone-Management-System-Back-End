package com.agri_drone_management_system_back_end.controller;

import com.agri_drone_management_system_back_end.entity.Booking;
import com.agri_drone_management_system_back_end.model.BookingStatus;
import com.agri_drone_management_system_back_end.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

//    @GetMapping
//    public ResponseEntity<List<Booking>> getAllBookings() {
//        return ResponseEntity.ok(bookingService.getAllBookings());
//    }

    @GetMapping
    public ResponseEntity<Page<Booking>> getBookings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "bookingDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        Page<Booking> bookingPage = bookingService.getPaginatedBookings(page, size, sortBy, sortDir);
        return ResponseEntity.ok(bookingPage);
    }

    @GetMapping("/operations")
    public ResponseEntity<Page<Booking>> searchBookings(
            @RequestParam(required = false) BookingStatus status,
            @RequestParam(required = false) String crop,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "bookingDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Booking> results = bookingService.getBookingsWithFilters(status, crop, search, pageable);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long bookingId) {
        return bookingService.getBookingById(bookingId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/pending-status")
    public ResponseEntity<List<Booking>> getPendingBookings() {
        return ResponseEntity.ok(bookingService.getBookingByStatus(BookingStatus.PENDING));
    }

    @GetMapping("/confirmed-status")
    public ResponseEntity<List<Booking>> getConfirmedBookings() {
        return ResponseEntity.ok(bookingService.getBookingByStatus(BookingStatus.CONFIRMED));
    }

    @GetMapping("/cancelled-status")
    public ResponseEntity<List<Booking>> getCancelledBookings() {
        return ResponseEntity.ok(bookingService.getBookingByStatus(BookingStatus.CANCELLED));
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking saved = bookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long bookingId, @RequestBody Booking booking) {
        return bookingService.updateBooking(bookingId, booking)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        if (bookingService.deleteBooking(bookingId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}