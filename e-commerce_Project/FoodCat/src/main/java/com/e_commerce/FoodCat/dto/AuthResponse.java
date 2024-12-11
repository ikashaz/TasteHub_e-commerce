package com.e_commerce.FoodCat.dto;

public class AuthResponse {
    private String token;
    private Long userId;

    public AuthResponse(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }

    // Getters and Setters
}

