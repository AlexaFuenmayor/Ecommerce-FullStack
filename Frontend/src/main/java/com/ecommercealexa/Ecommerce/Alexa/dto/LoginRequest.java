package com.ecommercealexa.Ecommerce.Alexa.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Se requiere el email")
    private String email;

    @NotBlank(message = "Se requiere la contraseña")
    private String password;
}
