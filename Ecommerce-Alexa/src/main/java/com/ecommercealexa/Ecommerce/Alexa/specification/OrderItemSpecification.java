package com.ecommercealexa.Ecommerce.Alexa.specification;

import com.ecommercealexa.Ecommerce.Alexa.entity.OrderItem;
import com.ecommercealexa.Ecommerce.Alexa.enums.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class OrderItemSpecification {

    /* Filtra los items de la orden por su estado (OrderStatus) */
    // La especificación verifica si el estado del item coincide con el que se pasa como parámetro.
    public static Specification<OrderItem> hasStatus(OrderStatus status){
        return ((root, query, criteriaBuilder) ->
                // Si el estado no es nulo, se aplica un filtro de igualdad sobre el campo 'status'
                status != null ? criteriaBuilder.equal(root.get("status"), status) : null);
    }

    /* Filtra los items de la orden por un rango de fechas */
    // La especificación permite filtrar los elementos de la orden según un rango de fechas de creación (createdAt).
    public static Specification<OrderItem> createBetween(LocalDateTime startDate, LocalDateTime endDate){
        return ((root, query, criteriaBuilder) -> {
            // Si ambas fechas de inicio y fin no son nulas, se aplica un filtro de rango de fechas
            if(startDate != null && endDate != null){
                return criteriaBuilder.between(root.get("createdAt"), startDate, endDate);
            }
            // Si solo se tiene la fecha de inicio, se filtra por fecha mayor o igual a la fecha de inicio
            else if (startDate != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), startDate);
            }
            // Si solo se tiene la fecha de fin, se filtra por fecha menor o igual a la fecha de fin
            else if (endDate != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), endDate);
            }else{
                // Si no se pasa ninguna fecha, no se aplica ningún filtro de fecha
                return null;
            }
        });
    }

    /* Filtra los items de la orden por el ID del ítem */
    // La especificación aplica un filtro de igualdad sobre el campo 'id' del item.
    public static Specification<OrderItem> hasItemId(Long itemId){
        return ((root, query, criteriaBuilder) ->
                // Si el ID del item no es nulo, se aplica un filtro de igualdad sobre el campo 'id'
                itemId != null ? criteriaBuilder.equal(root.get("id"), itemId) : null);
    }
}

