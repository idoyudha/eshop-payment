package com.github.eshop.payment.controller.rest;

import com.github.eshop.payment.application.exception.PaymentException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<ErrorResponse> handlePaymentException(PaymentException paymentException) {
        ErrorResponse errorResponse = new ErrorResponse(paymentException.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @Data
    @AllArgsConstructor
    static class ErrorResponse {
        private String message;
    }
}
