package com.ecommercealexa.Ecommerce.Alexa.service.interf;

import com.ecommercealexa.Ecommerce.Alexa.dto.CategoryDto; // DTO que contiene los datos de la categoría
import com.ecommercealexa.Ecommerce.Alexa.dto.Response; // DTO para la respuesta de la operación

// Interfaz que define los servicios para manejar las categorías en el sistema
public interface CategoryService {

    // Método para crear una nueva categoría en el sistema
    Response createCategory(CategoryDto categoryRequest);

    // Método para actualizar una categoría existente mediante su ID
    Response updateCategory(Long categoryId, CategoryDto categoryRequest);

    // Método para obtener todas las categorías disponibles
    Response getAllCategories();

    // Método para obtener una categoría específica por su ID
    Response getCategoryById(Long categoryId);

    // Método para eliminar una categoría mediante su ID
    Response deleteCategory(Long categoryId);
}

