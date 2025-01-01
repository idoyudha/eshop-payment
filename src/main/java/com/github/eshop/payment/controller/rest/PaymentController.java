package com.github.eshop.payment.controller.rest;

import java.util.List;
import java.util.UUID;

import com.github.eshop.payment.application.dto.ApiResponse;
import com.github.eshop.payment.application.dto.UpdatePaymentStatusRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.eshop.payment.application.dto.CreatePaymentRequest;
import com.github.eshop.payment.application.dto.PaymentResponse;
import com.github.eshop.payment.application.service.PaymentApplicationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentApplicationService paymentApplicationService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<PaymentResponse>> createPayment(@ModelAttribute CreatePaymentRequest request) {
        PaymentResponse payment = paymentApplicationService.createPayment(request);
        ApiResponse<PaymentResponse> response = ApiResponse.<PaymentResponse>builder()
                .code(HttpStatus.CREATED.value())
                .data(payment)
                .message("payment created successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> getAllPayments() {
        List<PaymentResponse> payments = paymentApplicationService.getAllPayments();
        ApiResponse<List<PaymentResponse>> response = ApiResponse.<List<PaymentResponse>>builder()
                .code(HttpStatus.OK.value())
                .data(payments)
                .message("payments retrieved successfully")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentResponse>> getPaymentById(@PathVariable UUID id) {
        PaymentResponse payment = paymentApplicationService.getPayment(id);
        ApiResponse<PaymentResponse> response = ApiResponse.<PaymentResponse>builder()
                .code(HttpStatus.OK.value())
                .data(payment)
                .message("payment retrieved successfully")
                .build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping()
    public ResponseEntity<ApiResponse<PaymentResponse>> updatePaymentStatus(@Valid @RequestBody UpdatePaymentStatusRequest request) {
        PaymentResponse payment = paymentApplicationService.updatePaymentStatus(request);
        ApiResponse<PaymentResponse> response = ApiResponse.<PaymentResponse>builder()
                .code(HttpStatus.OK.value())
                .data(payment)
                .message("payment status updated successfully")
                .build();
        return ResponseEntity.ok(response);
    }
}
