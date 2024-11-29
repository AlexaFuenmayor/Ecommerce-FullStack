package com.ecommercealexa.Ecommerce.Alexa.exception;

// Define una excepción personalizada que extiende RuntimeException.
public class NotFoundException extends RuntimeException {

    // Constructor que permite especificar un mensaje para la excepción.
    public NotFoundException(String message) {
        super(message); // Llama al constructor de la clase base (RuntimeException) con el mensaje proporcionado.
    }
}
