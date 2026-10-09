package com.agri_drone_management_system_back_end.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaymentRecordRequest {

    private BigDecimal amountReceived;
    private String paymentType; // e.g., "CASH", "UPI", "BANK_TRANSFER"
    private String paymentNotes;

}