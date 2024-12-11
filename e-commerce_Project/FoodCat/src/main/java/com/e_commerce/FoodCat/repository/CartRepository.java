package com.e_commerce.FoodCat.repository;

import com.e_commerce.FoodCat.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}
