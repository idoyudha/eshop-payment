package com.github.eshop.payment.application.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
public class CreatePaymentRequest {
    @NotNull(message = "Order ID is required")
    @JsonAlias("order_id")
    private UUID orderId;

    @NotNull(message = "Image is required")
    private MultipartFile image;

    private String note;
}
