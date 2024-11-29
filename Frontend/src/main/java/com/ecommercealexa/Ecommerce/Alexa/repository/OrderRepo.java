package com.ecommercealexa.Ecommerce.Alexa.repository;

import com.ecommercealexa.Ecommerce.Alexa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
