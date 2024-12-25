package com.github.eshop.payment.controller.event;

import com.github.eshop.payment.domain.entity.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PaymentUpdatedEvent {
    private UUID paymentId;
    private UUID orderId;
    private String imageUrl;
    private PaymentStatus status;
    private String note;
}
