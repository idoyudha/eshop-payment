package com.github.eshop.payment.infrastructure.persistence.entity;

import com.github.eshop.payment.domain.entity.PaymentStatus;
import com.github.eshop.payment.infrastructure.persistence.converter.UUIDConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Data
public class PaymentEntity {
    @Id
    @Convert(converter = UUIDConverter.class)
    private UUID id;

    @Column(name = "order_id", nullable = false)
    @Convert(converter = UUIDConverter.class)
    private UUID orderId;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;

    @Column(name = "note")
    private String note;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
