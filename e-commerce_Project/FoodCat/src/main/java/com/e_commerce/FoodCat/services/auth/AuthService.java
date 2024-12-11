package com.e_commerce.FoodCat.services.auth;

import com.e_commerce.FoodCat.dto.SignupRequest;
import com.e_commerce.FoodCat.dto.UserDto;
import com.e_commerce.FoodCat.entity.User;

import java.util.Optional;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);
    Boolean hasUserWithEmail(String email);
    User authenticate(String email, String rawPassword);
}
