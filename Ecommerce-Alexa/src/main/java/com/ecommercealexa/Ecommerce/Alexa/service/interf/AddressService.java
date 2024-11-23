package com.ecommercealexa.Ecommerce.Alexa.service.interf;

import com.ecommercealexa.Ecommerce.Alexa.dto.AddressDto; // DTO para la dirección del usuario
import com.ecommercealexa.Ecommerce.Alexa.dto.Response; // DTO para la respuesta

// Interfaz que define los métodos del servicio relacionados con las direcciones
public interface AddressService {

    /* Método para guardar o actualizar una dirección */
    // Recibe un AddressDto con los detalles de la dirección a guardar o actualizar
    Response saveAndUpdateAddress(AddressDto addressDto); // Retorna un objeto Response con el resultado de la operación
}

