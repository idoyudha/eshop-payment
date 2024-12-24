package com.github.eshop.payment.controller.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.eshop.payment.domain.entity.Payment;
import com.github.eshop.payment.domain.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentKafkaListener {
    private final PaymentService paymentService;
    private final ObjectMapper objectMapper;

//    @KafkaListener(topics = "payment-updated", groupId = "${spring.kafka.consumer.group-id")
//    public void handlePaymentUpdated(String message) {
//        try {
//            PaymentUpdatedEvent event = objectMapper.readValue(message, PaymentUpdatedEvent.class);
//            Payment payment = paymentService.updatePaymentStatus(event.getPaymentId(), event.getStatus());
//            log.info("Payment status updated for Id: {}", payment.getId());
//        } catch (Exception e) {
//            log.error("Failed to process payment updated event: {}", e.getMessage(), e);
//        }
//    }
}
