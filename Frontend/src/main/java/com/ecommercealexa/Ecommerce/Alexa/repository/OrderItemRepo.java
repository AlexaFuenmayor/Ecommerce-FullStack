package com.ecommercealexa.Ecommerce.Alexa.repository;

import com.ecommercealexa.Ecommerce.Alexa.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderItemRepo extends JpaRepository<OrderItem,Long>, JpaSpecificationExecutor<OrderItem> {
}
