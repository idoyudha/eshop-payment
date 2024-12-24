package com.github.eshop.payment.controller.rest;

import com.github.eshop.payment.application.dto.CreatePaymentRequest;
import com.github.eshop.payment.application.dto.PaymentResponse;
import com.github.eshop.payment.application.service.PaymentApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentApplicationService paymentApplicationService;

    // TODO: add cognito middleware
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PaymentResponse> createPayment(@ModelAttribute CreatePaymentRequest request) {
        PaymentResponse response = paymentApplicationService.createPayment(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // TODO: add cognito middleware
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable UUID id) {
        PaymentResponse response = paymentApplicationService.getPayment(id);
        return ResponseEntity.ok(response);
    }
}
