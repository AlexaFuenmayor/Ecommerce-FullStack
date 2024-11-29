package com.ecommercealexa.Ecommerce.Alexa.repository;

import com.ecommercealexa.Ecommerce.Alexa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
