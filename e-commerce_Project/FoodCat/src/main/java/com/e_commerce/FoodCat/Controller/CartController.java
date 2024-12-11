package com.e_commerce.FoodCat.Controller;

import com.e_commerce.FoodCat.dto.CartItemDto;
import com.e_commerce.FoodCat.dto.CheckoutRequest;
import com.e_commerce.FoodCat.dto.UpdateQuantityRequest;
import com.e_commerce.FoodCat.entity.Cart;
import com.e_commerce.FoodCat.entity.CartItem;
import com.e_commerce.FoodCat.entity.Orders;
import com.e_commerce.FoodCat.services.CartService;
import com.e_commerce.FoodCat.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

//    @GetMapping
//    public List<CartItem> getAllItems() {
//        return cartService.getAllItems();
//    }

    //display data in database
    @GetMapping
    public ResponseEntity<List<CartItemDto>> getCartItems(@RequestParam String username) {
        List<CartItemDto> cartItems = cartService.getCartItemsByUsername(username);
        return ResponseEntity.ok(cartItems);
    }

//    @PostMapping
//    public Cart addItem(@RequestBody Cart cart) {
//        return cartService.addItem(cart);
//    }

    @PostMapping("/add")
    public CartItem addToCart(@RequestParam String username,
                              @RequestParam Long productId,
                              @RequestParam int quantity) {
        return cartService.addToCart(username, productId, quantity);
    }


//    @PostMapping
//    public ResponseEntity<Cart> addCartItem(@RequestBody Cart cartItem) {
//        // Print the received data to the console
//        System.out.println("Received cart item: " + cartItem);
//
//        // Logic to save the cartItem to the database
//        Cart savedItem = cartService.save(cartItem);
//        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
//        cartService.deleteItem(id);
//        return ResponseEntity.ok().build();
//    }

    @DeleteMapping("/remove/{id}")
    public void removeFromCart(@RequestParam String username, @PathVariable Long id) {
        cartService.removeFromCart(username, id);
    }

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("/{userId}/add")
    public void addItemToCart(@PathVariable Long userId, @RequestBody CartItem item) {
        cartService.addCartItem(userId, item);
    }

    //update quantity
    // Controller method to handle the update
    @PutMapping("/update-quantity")
    public ResponseEntity<Void> updateQuantity(@RequestBody UpdateQuantityRequest request) {
        try {
            cartService.updateQuantity(request.getUsername(), request.getCartItemId(), request.getQuantity());
            return ResponseEntity.status(HttpStatus.OK).build(); // Return 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Return 400 if an error occurs
        }
    }

    //*____________________________________________________________________
    //order section
    @PostMapping("/{id}/checkout")
    public ResponseEntity<Orders> checkout(@PathVariable Long id, CheckoutRequest checkoutRequest) {
        try {
            Optional<CartItem> optionalCartItem = cartService.getCart(id);
            CartItem cartItem = optionalCartItem.orElseThrow(() ->
                    new IllegalArgumentException("Cart item not found for ID: " + id));

            double totalPrice = cartItem.getTotalPrice();
            Orders order = new Orders();
            order.setCustomerName(checkoutRequest.getUsername());
            order.setTotalPrice(totalPrice);

            // Attempt to create the order
            Orders createdOrder = orderService.createOrder(order);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);  // Successful response

        } catch (Exception e) {
            e.printStackTrace();  // Log the exception stack trace
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Return 500 status
        }
    }

}
