package com.ecommercealexa.Ecommerce.Alexa.service.impl;

import com.ecommercealexa.Ecommerce.Alexa.dto.AddressDto; // DTO que contiene los datos de la dirección
import com.ecommercealexa.Ecommerce.Alexa.dto.Response; // DTO para la respuesta de la operación
import com.ecommercealexa.Ecommerce.Alexa.entity.Address; // Entidad de la dirección
import com.ecommercealexa.Ecommerce.Alexa.entity.User; // Entidad de usuario
import com.ecommercealexa.Ecommerce.Alexa.repository.AddressRepo; // Repositorio para interactuar con la base de datos de direcciones
import com.ecommercealexa.Ecommerce.Alexa.service.interf.AddressService; // Interfaz del servicio de direcciones
import com.ecommercealexa.Ecommerce.Alexa.service.interf.UserService; // Interfaz del servicio de usuarios
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // Marca la clase como un servicio de Spring
@RequiredArgsConstructor // Genera un constructor con los campos final que serán autowired por Spring
public class AddressServiceImpl implements AddressService {

    private final AddressRepo addressRepo; // Repositorio de direcciones
    private final UserService userService; // Servicio para obtener la información del usuario autenticado

    @Override
    public Response saveAndUpdateAddress(AddressDto addressDto) {

        // Obtener el usuario autenticado desde el servicio de usuarios
        User user = userService.getLoginUser();

        // Obtener la dirección del usuario (si existe)
        Address address = user.getAddress();

        // Si no existe una dirección asociada, crear una nueva dirección
        if (address == null) {
            address = new Address();
            address.setUser(user); // Asociar la dirección con el usuario
        }

        // Actualizar los campos de la dirección con los valores recibidos en el DTO
        if (addressDto.getStreet() != null) address.setStreet(addressDto.getStreet());
        if (addressDto.getCity() != null) address.setCity(addressDto.getCity());
        if (addressDto.getState() != null) address.setState(addressDto.getState());
        if (addressDto.getCountry() != null) address.setCountry(addressDto.getCountry());

        // Guardar o actualizar la dirección en la base de datos
        addressRepo.save(address);

        // Determinar el mensaje según si la dirección fue creada o actualizada
        String message = (user.getAddress() == null) ? "Dirección creada" : "Dirección actualizada";

        // Retornar la respuesta con el mensaje de éxito
        return Response.builder()
                .status(200) // Código de estado HTTP
                .message(message) // Mensaje de la operación
                .build(); // Construir el objeto Response
    }
}

