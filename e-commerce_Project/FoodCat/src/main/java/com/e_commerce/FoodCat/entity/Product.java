package com.e_commerce.FoodCat.entity;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private String category;

    private String image_path;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImage_path() { return image_path; }
    public void setImagePath(String imagePath) { this.image_path = imagePath; }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category=category;
    }
}
