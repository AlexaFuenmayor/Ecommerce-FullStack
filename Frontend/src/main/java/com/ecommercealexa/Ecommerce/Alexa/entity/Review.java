package com.ecommercealexa.Ecommerce.Alexa.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name= "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //descripción
    private String content;
    private int rating; // 1 al 10

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name= "created_at")
    private final LocalDateTime createdAt = LocalDateTime.now();
}