package com.ecommercealexa.Ecommerce.Alexa.exception;

import com.ecommercealexa.Ecommerce.Alexa.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

// Indicar que esta clase manejará de manera global las excepciones de toda la aplicación.
@ControllerAdvice
public class GlobalHandlerException extends RuntimeException {

    // Este método manejará cualquier excepción genérica no controlada que ocurra en la aplicación.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleAllException(Exception ex, WebRequest request) {
        // Crear un objeto de respuesta con el estado 500 y el mensaje de error correspondiente.
        Response errorResponse = Response.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value()) // Asignar el código de estado HTTP 500.
                .message(ex.getMessage()) // Obtener el mensaje de la excepción.
                .build();

        // Devolver la respuesta con el estado HTTP 500.
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Este método manejará las excepciones de tipo NotFoundException.
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handleNotFoundException(NotFoundException ex, WebRequest request) {
        // Crear un objeto de respuesta con el estado 404 y el mensaje de error correspondiente.
        Response errorResponse = Response.builder()
                .status(HttpStatus.NOT_FOUND.value()) // Asignar el código de estado HTTP 404.
                .message(ex.getMessage()) // Obtener el mensaje de la excepción.
                .build();

        // Devolver la respuesta con el estado HTTP 404.
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Este método manejará las excepciones de tipo InvalidCredentialsException.
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Response> handleInvalidCredentialsException(InvalidCredentialsException ex, WebRequest request) {
        // Crear un objeto de respuesta con el estado 400 y el mensaje de error correspondiente.
        Response errorResponse = Response.builder()
                .status(HttpStatus.BAD_REQUEST.value()) // Asignar el código de estado HTTP 400.
                .message(ex.getMessage()) // Obtener el mensaje de la excepción.
                .build();

        // Devolver la respuesta con el estado HTTP 400.
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

