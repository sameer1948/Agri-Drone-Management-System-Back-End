package com.agri_drone_management_system_back_end.service;

import com.agri_drone_management_system_back_end.dto.PaymentRecordRequest;
import com.agri_drone_management_system_back_end.entity.Payment;
import com.agri_drone_management_system_back_end.model.PaymentStatus;
import com.agri_drone_management_system_back_end.repository.PaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Retrieve all payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Retrieve single payment by ID
    public Optional<Payment> getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId);
    }

    // Retrieve single payment by Log ID
    public Optional<Payment> getPaymentByLogId(Long logId) {
        return paymentRepository.findByLogId(logId);
    }

    // Paginated & sorted retrieval
    public Page<Payment> getPaginatedPayments(int page, int size, String sortBy, String sortDir,
                                              PaymentStatus status, String paymentType) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return paymentRepository.findWithFilters(status, paymentType, pageable);
    }

    // Create an initial Payment ledger entry (e.g., when Spraying is confirmed)
    @Transactional
    public Payment createInitialPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Record an incoming payment (Partial or Full)
    @Transactional
    public Payment processPayment(Long paymentId, PaymentRecordRequest request) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + paymentId));

        BigDecimal incoming = request.getAmountReceived();
        if (incoming == null || incoming.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount received must be greater than zero.");
        }

        // Add to existing paid amount
        BigDecimal newPaidAmount = (payment.getPaidAmount() != null ? payment.getPaidAmount() : BigDecimal.ZERO).add(incoming);
        payment.setPaidAmount(newPaidAmount);

        // Recalculate pending amount
        BigDecimal newPending = payment.getTotalAmount().subtract(newPaidAmount);
        if (newPending.compareTo(BigDecimal.ZERO) <= 0) {
            payment.setPendingAmount(BigDecimal.ZERO);
            payment.setPaymentStatus(PaymentStatus.PAID);
        } else {
            payment.setPendingAmount(newPending);
            // Remains PENDING
            payment.setPaymentStatus(PaymentStatus.PENDING);
        }

        // Update timestamps & audit fields
        payment.setLastPaymentDate(LocalDate.now());
        payment.setLastPaymentTime(LocalTime.now().withNano(0));
        payment.setPaymentType(request.getPaymentType());

        if (request.getPaymentNotes() != null && !request.getPaymentNotes().isBlank()) {
            payment.setPaymentNotes(request.getPaymentNotes());
        }

        return paymentRepository.save(payment);
    }

    // Direct update
    @Transactional
    public Payment updatePayment(Long paymentId, Payment updatedPayment) {
        return paymentRepository.findById(paymentId).map(existing -> {
            existing.setPaymentDate(updatedPayment.getPaymentDate());
            existing.setPaymentTime(updatedPayment.getPaymentTime());
            existing.setTotalAmount(updatedPayment.getTotalAmount());
            existing.setPaidAmount(updatedPayment.getPaidAmount());
            existing.setPendingAmount(updatedPayment.getPendingAmount());
            existing.setPaymentStatus(updatedPayment.getPaymentStatus());
            existing.setPaymentType(updatedPayment.getPaymentType());
            existing.setPaymentNotes(updatedPayment.getPaymentNotes());
            existing.setLastPaymentDate(updatedPayment.getLastPaymentDate());
            existing.setLastPaymentTime(updatedPayment.getLastPaymentTime());
            return paymentRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Payment not found with ID: " + paymentId));
    }

    // Delete payment
    @Transactional
    public boolean deletePayment(Long paymentId) {
        if (paymentRepository.existsById(paymentId)) {
            paymentRepository.deleteById(paymentId);
            return true;
        }
        return false;
    }
}