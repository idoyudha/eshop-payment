package com.github.eshop.payment.domain.service;

import com.github.eshop.payment.domain.entity.Payment;
import com.github.eshop.payment.domain.entity.PaymentStatus;
import com.github.eshop.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment getPayment(UUID id) {
        return paymentRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public Payment updatePaymentStatus(UUID id, PaymentStatus status) {
        Payment payment = getPayment(id);
        payment.setStatus(status);
        payment.setUpdatedAt(LocalDateTime.now());
        return paymentRepository.save(payment);
    }
}
