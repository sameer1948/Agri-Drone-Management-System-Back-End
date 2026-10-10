package com.agri_drone_management_system_back_end.controller;

import com.agri_drone_management_system_back_end.dto.PaymentRecordRequest;
import com.agri_drone_management_system_back_end.entity.Payment;
import com.agri_drone_management_system_back_end.model.PaymentStatus;
import com.agri_drone_management_system_back_end.service.PaymentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // GET /api/payments
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    // GET /api/payments/search (Paginated, sorted, filtered)
    @GetMapping("/search")
    public ResponseEntity<Page<Payment>> getPaginatedPayments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "paymentId") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(required = false) PaymentStatus status,
            @RequestParam(required = false) String paymentType) {

        Page<Payment> result = paymentService.getPaginatedPayments(page, size, sortBy, sortDir, status, paymentType);
        return ResponseEntity.ok(result);
    }

    // GET /api/payments/{paymentId}
    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET /api/payments/log/{logId}
    @GetMapping("/log/{logId}")
    public ResponseEntity<Payment> getPaymentByLogId(@PathVariable Long logId) {
        return paymentService.getPaymentByLogId(logId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /api/payments (Create new ledger entry)
    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        Payment saved = paymentService.createInitialPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // POST /api/payments/{paymentId}/record (Receive money via Cash, UPI, etc.)
    @PostMapping("/{paymentId}/record")
    public ResponseEntity<Payment> recordIncomingPayment(
            @PathVariable Long paymentId,
            @RequestBody PaymentRecordRequest request) {
        Payment updated = paymentService.processPayment(paymentId, request);
        return ResponseEntity.ok(updated);
    }

    // PUT /api/payments/{paymentId}
    @PutMapping("/{paymentId}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long paymentId, @RequestBody Payment payment) {
        Payment updated = paymentService.updatePayment(paymentId, payment);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/payments/{paymentId}
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long paymentId) {
        if (paymentService.deletePayment(paymentId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}