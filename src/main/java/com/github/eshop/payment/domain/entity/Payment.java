package com.github.eshop.payment.domain.entity;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Payment {
    private UUID id;
    private UUID orderId;
    private String imageUrl;
    private Double totalAmount;
    private PaymentStatus status;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
