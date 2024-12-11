package com.e_commerce.FoodCat.services;

import com.e_commerce.FoodCat.dto.CartItemDto;
import com.e_commerce.FoodCat.entity.Cart;
import com.e_commerce.FoodCat.entity.CartItem;
import com.e_commerce.FoodCat.entity.Product;
import com.e_commerce.FoodCat.entity.User;
import com.e_commerce.FoodCat.repository.CartItemRepository;
import com.e_commerce.FoodCat.repository.CartRepository;
import com.e_commerce.FoodCat.repository.ProductRepository;
import com.e_commerce.FoodCat.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> getAllItems() {
        return cartItemRepository.findAll();
    }


    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public CartItem addToCart(String username, Long productId, int quantity) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if product already exists in the user's cart
        Optional<CartItem> existingCartItem = cartItemRepository.findByUser(user).stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();
        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            return cartItemRepository.save(cartItem);
        }

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        // Set product name and price in the CartItem
        cartItem.setProductName(product.getName());
        cartItem.setPrice(product.getPrice());
        cartItem.setImage_path(product.getImage_path());
        cartItem.setTotalPrice(product.getPrice()*quantity);
        System.out.println("Total price set: " + cartItem.getTotalPrice()); // Debugging output
        return cartItemRepository.save(cartItem);
    }

    public void addCartItem(Long userId, CartItem item) {
        Cart cart = getCartByUserId(userId);
        if (cart != null) {
            item.setCart(cart);
            cart.getItems().add(item);
            cartRepository.save(cart);
        }
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteItem(Long id) {
        cartRepository.deleteById(id);
    }

    public void removeFromCart(String username, Long cartItemId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // Fetch the user for the cart item if not already loaded
        User cartItemUser = cartItem.getUser();
        if (cartItemUser == null) {
            throw new RuntimeException("Cart item user not found");
        }
//
//        // Check if the cart item belongs to the logged-in user
//        if (cartItemUser.getId() != user.getId()) {  // Use '==' to compare primitive long values
//            throw new RuntimeException("Unauthorized action");
//        }
        // Proceed with removing the item from the cart
        cartItemRepository.delete(cartItem);
    }

    //display item in cart for specified user
    public List<CartItemDto> getCartItemsByUsername(String username) {
        return cartItemRepository.findByUserUsername(username).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CartItemDto convertToDto(CartItem cartItem) {
        return new CartItemDto(
                cartItem.getId(),
                cartItem.getProduct().getName(),
                cartItem.getProduct().getImage_path(),
                cartItem.getProduct().getPrice(),
                cartItem.getQuantity(),
                cartItem.getTotalPrice()
        );
    }

    //update cart quantity
    public void updateQuantity(String username, Long cartItemId, int quantity) {
        // Retrieve the cart item by ID and username
        CartItem cartItem = cartItemRepository.findByIdAndUser_Username(cartItemId, username)
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found"));

        // Update the quantity
        cartItem.setQuantity(quantity);
        //update the total price after quantity updated
        cartItem.setTotalPrice(cartItem.getPrice()*quantity);

        // Save the updated cart item back to the database
        cartItemRepository.save(cartItem);
    }

    //order section
    public Optional<CartItem> getCart(Long id) {
        return cartItemRepository.findById(id);
    }

    //delete cart item after checkout
    // Method to clear the cart for a specific user
    @Transactional(propagation = Propagation.REQUIRED)
    public void clearCartByUserId(Long userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}