package com.e_commerce.FoodCat.dto;

public class CartItemDto {
    private Long id;
    private String productName;
    private String productImg;
    private double price;
    private int quantity;
    private double totalPrice;

    // Constructor
    public CartItemDto(Long id, String productName, String productImg, double price, int quantity,double totalPrice) {
        this.id = id;
        this.productName = productName;
        this.productImg = productImg;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice=totalPrice;
    }

    // Getters and setters

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
