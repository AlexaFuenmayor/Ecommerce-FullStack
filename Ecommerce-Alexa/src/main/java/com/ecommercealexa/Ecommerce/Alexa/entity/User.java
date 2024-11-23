package com.ecommercealexa.Ecommerce.Alexa.entity;


import com.ecommercealexa.Ecommerce.Alexa.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name="users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Se requiere que ingrese su nombre")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "Se requiere que ingrese un email")
    private String email;

    @NotBlank(message = "Se requiere que ingrese una contraseña")
    private String password;

    @Column(name = "phone_number")
    @NotBlank(message = "Se requiere que ingrese un número de teléfono")
    private String phoneNumber;
    private UserRole role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;

    @Column(name = "created_at")
    private final LocalDateTime createdAt = LocalDateTime.now();
}
