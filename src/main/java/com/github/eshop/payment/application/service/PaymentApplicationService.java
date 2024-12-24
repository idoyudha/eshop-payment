package com.github.eshop.payment.application.service;

import com.github.eshop.payment.application.dto.CreatePaymentRequest;
import com.github.eshop.payment.application.dto.PaymentResponse;
import com.github.eshop.payment.application.exception.PaymentException;
import com.github.eshop.payment.domain.entity.Payment;
import com.github.eshop.payment.domain.entity.PaymentStatus;
import com.github.eshop.payment.domain.service.PaymentService;
import com.github.eshop.payment.infrastructure.s3.S3Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentApplicationService {
    private final PaymentService paymentService;
    private final S3Service s3Service;

    @Transactional
    public PaymentResponse createPayment(CreatePaymentRequest request) {
        try {
            log.info("received payment request: {}", request);
            String imageUrl = s3Service.uploadImage(request.getImage());

            Payment payment = Payment.builder()
                    .id(UUID.randomUUID())
                    .orderId(request.getOrderId())
                    .imageUrl(imageUrl)
                    .status(PaymentStatus.PENDING)
                    .note(request.getNote())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            log.info("created payment entity: {}", payment);
            Payment savedPayment = paymentService.createPayment(payment);

            return paymentEntityToPaymentResponse(savedPayment);
        } catch (IOException e) {
            log.error("failed to upload payment image: {}", e.getMessage());
            throw new PaymentException("failed to upload payment image: " + e.getMessage());
        }

    }

    public PaymentResponse getPayment(UUID id) {
        Payment payment = paymentService.getPayment(id);
        return paymentEntityToPaymentResponse(payment);
    }

    private PaymentResponse paymentEntityToPaymentResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .imageUrl(payment.getImageUrl())
                .status(payment.getStatus())
                .note(payment.getNote())
                .createdAt(payment.getCreatedAt())
                .updatedAt(payment.getUpdatedAt())
                .build();
    }
}
