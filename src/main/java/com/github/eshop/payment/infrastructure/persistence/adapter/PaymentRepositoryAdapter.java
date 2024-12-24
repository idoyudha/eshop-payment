package com.github.eshop.payment.infrastructure.persistence.adapter;

import com.github.eshop.payment.domain.entity.Payment;
import com.github.eshop.payment.domain.repository.PaymentRepository;
import com.github.eshop.payment.infrastructure.persistence.entity.PaymentEntity;
import com.github.eshop.payment.infrastructure.persistence.repository.JpaPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepository {
    private final JpaPaymentRepository jpaPaymentRepository;

    @Override
    public Payment save(Payment payment) {
        PaymentEntity entity = mapToEntity(payment);
        PaymentEntity savedEntity = jpaPaymentRepository.save(entity);
        return mapToDomain(savedEntity);
    }

    @Override
    public Optional<Payment> findById(UUID id) {
        return jpaPaymentRepository.findById(id)
                .map(this::mapToDomain);
    }


    private PaymentEntity mapToEntity(Payment payment) {
        PaymentEntity entity = new PaymentEntity();
        entity.setId(payment.getId());
        entity.setOrderId(payment.getOrderId());
        entity.setImageUrl(payment.getImageUrl());
        entity.setStatus(payment.getStatus());
        entity.setNote(payment.getNote());
        entity.setCreatedAt(payment.getCreatedAt());
        entity.setUpdatedAt(payment.getUpdatedAt());
        entity.setDeletedAt(payment.getDeletedAt());
        return entity;
    }

    private Payment mapToDomain(PaymentEntity entity) {
        return Payment.builder()
                .id(entity.getId())
                .orderId(entity.getOrderId())
                .imageUrl(entity.getImageUrl())
                .status(entity.getStatus())
                .note(entity.getNote())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }
}
