package com.ecommercealexa.Ecommerce.Alexa.mapper;

import com.ecommercealexa.Ecommerce.Alexa.dto.*;
import com.ecommercealexa.Ecommerce.Alexa.entity.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component // Marcar esta clase como un componente de Spring para ser gestionada por el contenedor IoC.
public class EntityDtoMapper {

    // Mapear un objeto User (entidad) a un UserDto con información básica.
    public UserDto mapUserToDtoBasic(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole().name());
        userDto.setName(user.getName());
        return userDto;
    }

    // Mapear un objeto Address (entidad) a un AddressDto con información básica.
    public AddressDto mapAddressToDtoBasic(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setStreet(address.getStreet());
        addressDto.setCountry(addressDto.getCountry());
        return addressDto;
    }

    // Mapea un objeto Category (entidad) a un CategoryDto con información básica.
    public CategoryDto mapCategoryToDtoBasic(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    // Mapear un objeto OrderItem (entidad) a un OrderItemDto con información básica.
    public OrderItemDto mapOrderItemToDtoBasic(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setStatus(orderItem.getStatus().name());
        orderItemDto.setCreatedAt(orderItem.getCreatedAt());
        return orderItemDto;
    }

    // Mapear un objeto Product (entidad) a un ProductDto con información básica.
    public ProductDto mapProductToDtoBasic(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        return productDto;
    }

    // Mapear un User a un UserDto con información básica y su dirección asociada.
    public UserDto mapUserToDtoPlusAddress(User user) {
        UserDto userDto = mapUserToDtoBasic(user); // Reutiliza el método de mapeo básico de usuario.
        if (user.getAddress() != null) {
            AddressDto addressDto = mapAddressToDtoBasic(user.getAddress()); // Mapea la dirección si existe.
            userDto.setAddress(addressDto);
        }
        return userDto;
    }

    // Mapear un OrderItem a un OrderItemDto incluyendo información del producto.
    public OrderItemDto mapOrderItemToDtoPLusProduct(OrderItem orderItem) {
        OrderItemDto orderItemDto = mapOrderItemToDtoBasic(orderItem); // Reutiliza el mapeo básico de OrderItem.
        if (orderItemDto.getProduct() != null) {
            ProductDto productDto = mapProductToDtoBasic(orderItem.getProduct()); // Mapea el producto si existe.
            orderItemDto.setProduct(productDto);
        }
        return orderItemDto;
    }

    // Mapear un OrderItem a un OrderItemDto incluyendo producto y usuario asociados.
    public OrderItemDto mapOrderItemToDtoPlusProductAndUser(OrderItem orderItem) {
        OrderItemDto orderItemDto = mapOrderItemToDtoPLusProduct(orderItem); // Reutiliza el mapeo de producto.
        if (orderItem.getUser() != null) {
            UserDto userDto = mapUserToDtoPlusAddress(orderItem.getUser()); // Mapea el usuario si existe.
            orderItemDto.setUser(userDto);
        }
        return orderItemDto;
    }

    // Mapear un User a un UserDto incluyendo su dirección y el historial de OrderItems.
    public UserDto mapUserToDtoPlusAddressAndOrderHistory(User user) {
        UserDto userDto = mapUserToDtoPlusAddress(user); // Reutiliza el mapeo de dirección y usuario básico.
        if (user.getOrderItemList() != null && !user.getOrderItemList().isEmpty()) {
            // Convierte cada OrderItem a su DTO correspondiente incluyendo el producto.
            userDto.setOrderItemList(user.getOrderItemList()
                    .stream()
                    .map(this::mapOrderItemToDtoPLusProduct)
                    .collect(Collectors.toList()));
        }
        return userDto;
    }
}

