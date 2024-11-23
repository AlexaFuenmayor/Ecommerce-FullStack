package com.ecommercealexa.Ecommerce.Alexa.security;

import com.ecommercealexa.Ecommerce.Alexa.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;


// Clase para configurar una clave secreta para firmar tokens JWT,
// usando @Value para obtener el secreto desde un archivo de configuración.
@Service
@Slf4j
public class JwtUtils {
    // Tiempo de expiración del token en milisegundos: 6 meses
    private static final long EXPIRATION_TIME_IN_MILLISECOND = 100L * 60L * 24L * 30L * 6L;
    private SecretKey key;

    @Value("${secreteJwtString}")
    private String secreteJwtString; // Valor configurado en application.properties; debe tener al menos 32 caracteres

    @PostConstruct
    private void init() {
        // Inicializa la clave secreta para firmar los tokens a partir de la cadena configurada
        byte[] keyBytes = secreteJwtString.getBytes(StandardCharsets.UTF_8);
        this.key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    // Genera un token JWT usando el correo del usuario como identificador
    public String generateToken(User user) {
        String username = user.getEmail();
        return generateToken(username);
    }

    // Genera un token JWT para un nombre de usuario específico
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username) // Establece el sujeto del token (username)
                .issuedAt(new Date(System.currentTimeMillis())) // Fecha de emisión
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MILLISECOND)) // Fecha de expiración
                .signWith(key) // Firma el token con la clave secreta
                .compact(); // Devuelve el token como una cadena compacta
    }

    // Extrae el nombre de usuario (subject) del token JWT
    public String getUsernameFromToken(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    // Método genérico para extraer información de las claims del token JWT
    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        // Valida y decodifica el token para obtener las claims
        return claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }

    // Valida si el token pertenece al usuario dado y no ha expirado
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token); // Obtiene el username del token
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); // Verifica coincidencia y expiración
    }

    // Verifica si el token ha expirado comparando su fecha de expiración con la actual
    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}

