package com.github.eshop.payment.infrastructure.security.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthResponse {
    private int code;
    private AuthData data;
    private String message;

    @Data
    public static class AuthData {
        private UUID userId;
        private String role;
    }
}
