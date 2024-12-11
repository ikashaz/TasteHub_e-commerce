package com.e_commerce.FoodCat.repository;


import com.e_commerce.FoodCat.entity.User;
import com.e_commerce.FoodCat.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //write the query that we need here(replace sql query)
    Optional<User> findFirstByEmail(String email);

    //User findByRole(UserRole userRole);
    List<User> findByRole(UserRole role);

    Optional<User> findByUsername(String username);
}
