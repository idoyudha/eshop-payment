package com.github.eshop.payment.infrastructure.security;

import com.github.eshop.payment.infrastructure.security.dto.AuthResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    @Value("${auth.base-url}")
    private String authBaseUrl;

    private final RestTemplate restTemplate;

    public AuthenticationFilter() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request);

        if (token == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        try {
            String authUrl = String.format("%s/v1/auth/%s", authBaseUrl, token);
            AuthResponse authResponse = restTemplate.getForObject(authUrl, AuthResponse.class);

            if (authResponse != null && authResponse.getData() != null) {
                request.setAttribute("userId", authResponse.getData().getUserId());
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        } catch (Exception e) {
            log.error("error authenticating token: {}", e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7).trim();
        }
        return null;
    }
}
