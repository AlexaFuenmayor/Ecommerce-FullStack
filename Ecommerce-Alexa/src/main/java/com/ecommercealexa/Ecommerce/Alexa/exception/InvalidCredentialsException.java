package com.ecommercealexa.Ecommerce.Alexa.exception;


// Clase que representa una excepción para credenciales inválidas.
// Extiende RuntimeException, permitiendo lanzar esta excepción en tiempo de ejecución.
public class InvalidCredentialsException extends RuntimeException {

    // Constructor que recibe un mensaje y lo pasa al constructor de RuntimeException.
    public InvalidCredentialsException(String message) {
        super(message); // Inicializa la excepción con un mensaje específico.
    }
}
