package com.ecommercealexa.Ecommerce.Alexa.service.impl;

import com.ecommercealexa.Ecommerce.Alexa.dto.LoginRequest;
import com.ecommercealexa.Ecommerce.Alexa.dto.Response;
import com.ecommercealexa.Ecommerce.Alexa.dto.UserDto;
import com.ecommercealexa.Ecommerce.Alexa.entity.User;
import com.ecommercealexa.Ecommerce.Alexa.enums.UserRole;
import com.ecommercealexa.Ecommerce.Alexa.exception.InvalidCredentialsException;
import com.ecommercealexa.Ecommerce.Alexa.exception.NotFoundException;
import com.ecommercealexa.Ecommerce.Alexa.mapper.EntityDtoMapper;
import com.ecommercealexa.Ecommerce.Alexa.repository.UserRepo;
import com.ecommercealexa.Ecommerce.Alexa.security.JwtUtils;
import com.ecommercealexa.Ecommerce.Alexa.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor // Constructor para la inyección de dependencias
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo; // Repositorio para manejar la entidad User
    private final PasswordEncoder passwordEncoder; // Codificador de contraseñas
    private final JwtUtils jwtUtils; // Utilidades para trabajar con JWT
    private final EntityDtoMapper entityDtoMapper; // Mapeo de entidades a DTOs

    /* Método para registrar un nuevo usuario */
    // Si el rol proporcionado es "admin", se asigna el rol de "ADMIN", de lo contrario, se asigna "USER".
    @Override
    public Response registerUser(UserDto registrationRequest) {
        UserRole role = UserRole.USER;

        // Verificar si el rol solicitado es "admin" y asignar el rol correspondiente
        if (registrationRequest.getRole() != null && registrationRequest.getRole().equalsIgnoreCase("admin")) {
            role = UserRole.ADMIN;
        }

        // Crear un objeto User usando los datos proporcionados en el DTO
        User user = User.builder()
                .name(registrationRequest.getName())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword())) // Codificar la contraseña
                .role(role)
                .build();

        // Guardar el usuario en la base de datos
        User savedUser = userRepo.save(user);

        // Convertir el usuario guardado a un DTO para devolverlo en la respuesta
        UserDto userDto = entityDtoMapper.mapUserToDtoBasic(savedUser);

        // Retornar la respuesta con el estado y los datos del nuevo usuario
        return Response.builder()
                .status(200)
                .message("User agregado exitosamente")
                .user(userDto)
                .build();
    }

    /* Método para iniciar sesión con las credenciales del usuario */
    // Verifica si el correo existe y si la contraseña proporcionada coincide con la almacenada.
    @Override
    public Response loginUser(LoginRequest loginRequest) {
        // Buscar al usuario por correo electrónico
        User user = userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new NotFoundException("Email no encontrado"));

        // Verificar si la contraseña es correcta
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Password does not match");
        }

        // Generar un token JWT para el usuario
        String token = jwtUtils.generateToken(user);

        // Retornar la respuesta con el estado, mensaje, token y rol del usuario
        return Response.builder()
                .status(200)
                .message("El usuario se registro satisfactoriamente")
                .token(token)
                .expirationTime("6 Meses") // Tiempo de expiración del token
                .role(user.getRole().name())
                .build();
    }

    /* Método para obtener todos los usuarios registrados */
    // Convierte cada entidad User en un DTO para la respuesta
    @Override
    public Response getAllUsers() {
        List<User> users = userRepo.findAll(); // Obtener todos los usuarios de la base de datos
        // Convertir cada entidad User a un DTO básico
        List<UserDto> userDtos = users.stream()
                .map(entityDtoMapper::mapUserToDtoBasic)
                .toList();

        // Retornar la lista de usuarios en la respuesta
        return Response.builder()
                .status(200)
                .userList(userDtos) // Lista de usuarios en formato DTO
                .build();
    }

    /* Método para obtener el usuario actualmente autenticado */
    // Recupera el correo del usuario desde el contexto de seguridad
    @Override
    public User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Obtener autenticación actual
        String email = authentication.getName(); // Recuperar el nombre (correo electrónico) del usuario autenticado
        log.info("El email del usuario es: " + email);

        // Buscar al usuario por su correo y lanzando excepción si no se encuentra
        return userRepo.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("El usuario no se encontró"));
    }

    /* Método para obtener la información del usuario y su historial de pedidos */
    // Llama a getLoginUser para obtener al usuario actual y luego mapea sus datos a un DTO más completo (con dirección e historial de pedidos)
    @Override
    public Response getUserInfoAndOrderHistory() {
        User user = getLoginUser(); // Obtener el usuario autenticado
        UserDto userDto = entityDtoMapper.mapUserToDtoPlusAddressAndOrderHistory(user); // Mapear usuario a DTO con más información

        // Retornar la respuesta con el estado y los datos completos del usuario
        return Response.builder()
                .status(200)
                .user(userDto) // Usuario con información completa
                .build();
    }
}

