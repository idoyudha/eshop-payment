package com.github.eshop.payment.controller.event;

import com.github.eshop.payment.domain.entity.PaymentStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class PaymentUpdatedEvent {
    private UUID paymentId;
    private PaymentStatus status;
}
