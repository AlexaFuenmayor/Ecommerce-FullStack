package com.ecommercealexa.Ecommerce.Alexa.security;


import com.ecommercealexa.Ecommerce.Alexa.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
public class AuthUser implements UserDetails {

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retorna la lista de roles/autorizaciones del usuario
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword() {
        // Retorna la contraseña del usuario
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // Retorna el email del usuario como su nombre de usuario
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Indica que la cuenta no ha expirado
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Indica que la cuenta no está bloqueada
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Indica que las credenciales no han expirado
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Indica que la cuenta está habilitada
        return true;
    }
}
