package com.ecommercealexa.Ecommerce.Alexa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    // Configura las reglas de CORS para la aplicación
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Aplica las configuraciones de CORS a todas las rutas
                registry.addMapping("/**")
                        // Permite los métodos HTTP especificados
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        // Permite solicitudes de cualquier origen
                        .allowedOrigins("*");
            }
        };
    }
}

