package com.e_commerce.FoodCat.dto;

import lombok.*;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutRequest {
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }
}