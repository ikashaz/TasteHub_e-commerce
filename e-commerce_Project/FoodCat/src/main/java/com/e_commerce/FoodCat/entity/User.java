package com.e_commerce.FoodCat.entity;

import com.e_commerce.FoodCat.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data //for setter and getter
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //increment auto
    private long id;

    private String email;

    private String password;

    private String name;

    private String username;

    private UserRole role;

    @Lob //can use large data e.g for images
    @Column (columnDefinition = "longblob")
    private byte[] img;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Cart> carts;
}
