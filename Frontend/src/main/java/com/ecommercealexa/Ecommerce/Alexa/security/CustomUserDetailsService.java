package com.ecommercealexa.Ecommerce.Alexa.security;

import com.ecommercealexa.Ecommerce.Alexa.dto.UserDto;
import com.ecommercealexa.Ecommerce.Alexa.entity.User;
import com.ecommercealexa.Ecommerce.Alexa.exception.NotFoundException;
import com.ecommercealexa.Ecommerce.Alexa.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca al usuario por email en el repositorio, lanza excepción si no lo encuentra
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("User/Email Not found"));

        // Crea y retorna una instancia de AuthUser con los datos del usuario encontrado
        return AuthUser.builder()
                .user(user)
                .build();
    }
}

