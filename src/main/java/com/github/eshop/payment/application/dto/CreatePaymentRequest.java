package com.github.eshop.payment.application.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
public class CreatePaymentRequest {
//    @NotNull(message = "Order ID is required")
    private UUID orderId;

//    @NotNull(message = "Image is required")
    private MultipartFile image;

    private String note;
}
