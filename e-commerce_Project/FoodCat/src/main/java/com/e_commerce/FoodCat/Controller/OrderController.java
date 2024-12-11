package com.e_commerce.FoodCat.Controller;

import com.e_commerce.FoodCat.dto.OrderRequest;
import com.e_commerce.FoodCat.entity.Orders;
import com.e_commerce.FoodCat.services.CartService;
import com.e_commerce.FoodCat.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    private CartService cartService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

//    @PostMapping
//    public ResponseEntity<Orders> placeOrder(@RequestBody OrderRequest orderRequest) {
//        System.out.println("OrderRequest: " + orderRequest);
//        Orders order = orderService.placeOrder(orderRequest);
//        return ResponseEntity.ok(order);
//    }

    //place order
    @PostMapping
    public ResponseEntity<List<Orders>> placeOrder(@RequestBody List<OrderRequest> orderRequests) {
        // Log for debugging
        System.out.println("OrderRequests: " + orderRequests);

        // Process each order
        List<Orders> placedOrders = orderRequests.stream()
                .map(orderService::placeOrder)
                .collect(Collectors.toList());


        return ResponseEntity.ok(placedOrders);
    }

    //place order based on userId
    @PostMapping("/{userId}")
    public ResponseEntity<List<Orders>> placeOrder(@RequestBody List<OrderRequest> orderRequests,@PathVariable Long userId) {
        try {
            // Log incoming requests
            System.out.println("Received Order Requests: " + orderRequests);

            // Process orders
            List<Orders> placedOrders = orderRequests.stream()
                    .map(orderRequest -> {
                        System.out.println("Processing order for productId: " + orderRequest.getProductId());
                        return orderService.placeOrder(orderRequest);
                    })
                    .collect(Collectors.toList());

            // Clear cart after placing orders
            cartService.clearCartByUserId(userId);

            // Log the placed orders before sending the response
            System.out.println("Placed Orders: " + placedOrders);

            return ResponseEntity.ok(placedOrders);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(List.of(new Orders("Error processing orders")));
        }
    }


    //track order
    @GetMapping("/{orderId}")
    public Orders getOrder(@PathVariable Long orderId) {
        return orderService.getOrderByOrderId(orderId);
    }

    //display orders list based on user Id
    @GetMapping("/list/{userId}")
    public List<Orders> getOrders(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }
}
