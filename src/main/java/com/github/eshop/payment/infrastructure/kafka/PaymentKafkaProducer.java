package com.github.eshop.payment.infrastructure.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.eshop.payment.controller.event.PaymentUpdatedEvent;
import com.github.eshop.payment.domain.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${spring.kafka.topics.payment-updated}")
    private String paymentUpdatedTopic;

    public void publishPaymentUpdated(Payment payment) {
        try {
            PaymentUpdatedEvent event = PaymentUpdatedEvent.builder()
                    .paymentId(payment.getId())
                    .orderId(payment.getOrderId())
                    .imageUrl(payment.getImageUrl())
                    .status(payment.getStatus())
                    .note(payment.getNote())
                    .build();

            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(paymentUpdatedTopic, payment.getId().toString(), message);
            log.info("published payment updated event: {}", message);
        } catch (Exception e) {
            log.error("error publishing payment updated event: {}", e.getMessage());
        }
    }
}
