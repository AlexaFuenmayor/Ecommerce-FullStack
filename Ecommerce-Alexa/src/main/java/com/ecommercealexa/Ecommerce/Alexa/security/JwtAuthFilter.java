package com.ecommercealexa.Ecommerce.Alexa.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Obtiene el token JWT desde la solicitud
        String token = getTokenFromRequest(request);

        if (token != null) {
            // Extrae el nombre de usuario (username) del token JWT
            String username = jwtUtils.getUsernameFromToken(token);

            // Carga los detalles del usuario usando el username
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            // Verifica si el username es válido y si el token no ha expirado
            if (StringUtils.hasText(username) && jwtUtils.isTokenValid(token, userDetails)) {
                log.info("VALID JWT FOR {}", username);

                // Crea un objeto de autenticación con los detalles del usuario y sus permisos
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                // Asocia la autenticación con la solicitud actual
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Establece la autenticación en el contexto de seguridad de Spring
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // Continúa con la siguiente etapa de la cadena de filtros
        filterChain.doFilter(request, response);
    }

    // Extrae el token JWT del encabezado "Authorization" de la solicitud
    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        // Verifica que el token tenga texto y comience con "Bearer "
        if (StringUtils.hasText(token) && StringUtils.startsWithIgnoreCase(token, "Bearer ")) {
            return token.substring(7); // Devuelve el token sin el prefijo "Bearer "
        }
        return null; // Retorna null si el token no es válido
    }
}

