package com.github.eshop.payment.application.dto;

import com.github.eshop.payment.domain.entity.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdatePaymentStatusRequest {
    @NotNull(message = "Order ID is required")
    private UUID paymentId;

    @NotNull(message = "Status is required")
    private PaymentStatus status;
}
