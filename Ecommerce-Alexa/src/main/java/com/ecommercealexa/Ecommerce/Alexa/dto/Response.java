package com.ecommercealexa.Ecommerce.Alexa.dto;

import com.ecommercealexa.Ecommerce.Alexa.entity.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private int status;
    private String message;
    private final LocalDateTime timestamp = LocalDateTime.now();

    private String token;
    private String role;
    private String expirationTime;

    private int totalPage;
    private long totalElement;

    private AddressDto address;

    private UserDto user;
    private List<UserDto> userList;

    private CategoryDto categoryDto;
    private List<CategoryDto> categoryList;

    private ProductDto productDto;
    private List<ProductDto> productList;

    private OrderDto orderDto;
    private List<OrderDto> orderDtoList;

    private OrderItemDto orderItemDto;
    private List<OrderItemDto> orderItemDtoList;

}
