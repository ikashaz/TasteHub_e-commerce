package com.e_commerce.FoodCat.dto;

import lombok.Data;

@Data
public class SignupRequest {

    private String email;

    private String password;

    private String name;

    private String username;
}
