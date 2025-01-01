package com.github.eshop.payment.domain.service;

import com.github.eshop.payment.domain.entity.Payment;
import com.github.eshop.payment.domain.entity.PaymentStatus;
import com.github.eshop.payment.domain.repository.PaymentRepository;
import com.github.eshop.payment.infrastructure.kafka.PaymentKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentKafkaProducer paymentKafkaProducer;

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPayment(UUID id) {
        return paymentRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public Payment updatePaymentStatus(UUID id, PaymentStatus status) {
        Payment payment = getPayment(id);
        payment.setStatus(status);
        payment.setUpdatedAt(LocalDateTime.now());

        Payment updatedPayment = paymentRepository.save(payment);
        paymentKafkaProducer.publishPaymentUpdated(updatedPayment);

        return updatedPayment;
    }
}
