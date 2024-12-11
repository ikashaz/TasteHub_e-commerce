package com.e_commerce.FoodCat.repository;

import com.e_commerce.FoodCat.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    Orders findOrdersById(Long orderId);

    List<Orders> findByCustId(Long userId); //find orders by user Id

    //search for status
    @Query("SELECT o FROM Orders o WHERE o.status = :status")
    List<Orders> findByStatus(@Param("status") String status);
}

