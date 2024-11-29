package com.ecommercealexa.Ecommerce.Alexa.service.interf;

import com.ecommercealexa.Ecommerce.Alexa.dto.OrderRequest;
import com.ecommercealexa.Ecommerce.Alexa.dto.Response;
import com.ecommercealexa.Ecommerce.Alexa.enums.OrderStatus;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;

public interface OrderItemService {
    Response placeOrder(OrderRequest orderRequest);
    Response updateOrderItemStatus(Long orderItemId, String status);
    Response filterOrderItems(OrderStatus status, LocalDateTime startDate, LocalDateTime endDate, Long itemId, Pageable pageable);

}