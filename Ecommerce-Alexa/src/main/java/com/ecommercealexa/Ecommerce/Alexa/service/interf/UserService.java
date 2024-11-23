package com.ecommercealexa.Ecommerce.Alexa.service.interf;

import com.ecommercealexa.Ecommerce.Alexa.dto.LoginRequest;
import com.ecommercealexa.Ecommerce.Alexa.dto.Response;
import com.ecommercealexa.Ecommerce.Alexa.dto.UserDto;
import com.ecommercealexa.Ecommerce.Alexa.entity.User;

public interface UserService {

    /* Registra un nuevo usuario */
    // Este método recibe un objeto de tipo UserDto con la información del usuario y devuelve un Response que contiene el resultado del registro.
    Response registerUser(UserDto registrationRequest);

    /* Inicia sesión para un usuario */
    // Este método recibe un LoginRequest (contiene credenciales de usuario) y devuelve un Response con el resultado del inicio de sesión.
    Response loginUser(LoginRequest loginRequest);

    /* Obtiene todos los usuarios registrados */
    // Este método devuelve un Response que contiene la lista de todos los usuarios en el sistema.
    Response getAllUsers();

    /* Obtiene el usuario actualmente logueado */
    // Este método devuelve un objeto User, que representa al usuario que ha iniciado sesión.
    User getLoginUser();

    /* Obtiene la información del usuario y su historial de pedidos */
    // Este método devuelve un Response que contiene tanto la información del usuario como su historial de pedidos.
    Response getUserInfoAndOrderHistory();
}

