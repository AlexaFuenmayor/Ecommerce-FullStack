package com.ecommercealexa.Ecommerce.Alexa.repository;

import com.ecommercealexa.Ecommerce.Alexa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByNameOrDescription(String name, String description);
}
