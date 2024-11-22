package com.ecommercealexa.Ecommerce.Alexa.repository;

import com.ecommercealexa.Ecommerce.Alexa.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
