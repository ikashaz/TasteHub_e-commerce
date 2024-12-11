package com.e_commerce.FoodCat.repository;

import com.e_commerce.FoodCat.entity.Cart;
import com.e_commerce.FoodCat.entity.CartItem;
import com.e_commerce.FoodCat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    List<CartItem> findByUserUsername(String username);
    Optional<CartItem> findByIdAndUser_Username(Long id, String username);

    //for order
    Optional<CartItem> findById(Long id);
    void deleteByUserId(Long userId);
}
