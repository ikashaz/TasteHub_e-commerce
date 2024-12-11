package com.e_commerce.FoodCat.services;

import com.e_commerce.FoodCat.dto.OrderRequest;
import com.e_commerce.FoodCat.entity.Orders;
import com.e_commerce.FoodCat.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Orders createOrder(Orders order) {
        return orderRepository.save(order);
    }

    public Orders placeOrder(OrderRequest orderRequest) {
        Orders order = new Orders();
        order.setProductName(orderRequest.getProductName());
        order.setProductImage(orderRequest.getProductImage());
        order.setProductId(orderRequest.getProductId());
        order.setQuantity(orderRequest.getQuantity());
        order.setPrice(orderRequest.getPrice());
        order.setTotalPrice(orderRequest.getTotalPrice());
        order.setCustId(orderRequest.getCustId());
        order.setUsername(orderRequest.getUsername());
        order.setAddress(orderRequest.getAddress());
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setStatus("Pending"); // Default status
        return orderRepository.save(order);
    }

    public Orders getOrderByOrderId(Long orderId) {
        return orderRepository.findOrdersById(orderId);
    }

    public List<Orders> getOrdersByUserId(Long userId) {
        return orderRepository.findByCustId(userId);
    }
}
