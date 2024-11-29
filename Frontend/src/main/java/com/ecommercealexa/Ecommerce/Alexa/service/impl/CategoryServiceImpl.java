package com.ecommercealexa.Ecommerce.Alexa.service.impl;

import com.ecommercealexa.Ecommerce.Alexa.dto.CategoryDto;
import com.ecommercealexa.Ecommerce.Alexa.dto.Response;
import com.ecommercealexa.Ecommerce.Alexa.entity.Category;
import com.ecommercealexa.Ecommerce.Alexa.exception.NotFoundException;
import com.ecommercealexa.Ecommerce.Alexa.mapper.EntityDtoMapper;
import com.ecommercealexa.Ecommerce.Alexa.repository.CategoryRepo;
import com.ecommercealexa.Ecommerce.Alexa.service.interf.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // Indica que esta clase es un servicio gestionado por Spring
@RequiredArgsConstructor // Constructor automático para las dependencias necesarias
@Slf4j // Habilita el registro de logs en la clase
public class CategoryServiceImpl implements CategoryService {

    // Dependencias necesarias para el servicio
    private final CategoryRepo categoryRepo; // Repositorio para interactuar con la base de datos de categorías
    private final EntityDtoMapper entityDtoMapper; // Mapper para convertir entidades de categorías a DTOs

    // Método para crear una nueva categoría
    @Override
    public Response createCategory(CategoryDto categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName()); // Establece el nombre de la categoría desde el DTO
        categoryRepo.save(category); // Guarda la nueva categoría en la base de datos
        return Response.builder() // Crea y retorna una respuesta con éxito
                .status(200)
                .message("Categoría creada con éxito")
                .build();
    }

    // Método para actualizar una categoría existente
    @Override
    public Response updateCategory(Long categoryId, CategoryDto categoryRequest) {
        // Buscar la categoría por ID, si no se encuentra, lanza una excepción
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new NotFoundException("Categoría no encontrada"));
        category.setName(categoryRequest.getName()); // Actualiza el nombre de la categoría
        categoryRepo.save(category); // Guarda los cambios en la base de datos
        return Response.builder() // Crea y retorna una respuesta con éxito
                .status(200)
                .message("Categoría actualizada")
                .build();
    }

    // Método para obtener todas las categorías
    @Override
    public Response getAllCategories() {
        List<Category> categories = categoryRepo.findAll(); // Obtiene todas las categorías de la base de datos
        List<CategoryDto> categoryDtoList = categories.stream() // Convierte las categorías a DTOs
                .map(entityDtoMapper::mapCategoryToDtoBasic)
                .collect(Collectors.toList()); // Colecciona todos los DTOs en una lista

        return Response.builder() // Crea y retorna una respuesta con las categorías obtenidas
                .status(200)
                .categoryList(categoryDtoList)
                .build();
    }

    // Método para obtener una categoría específica por su ID
    @Override
    public Response getCategoryById(Long categoryId) {
        // Buscar la categoría por ID, si no se encuentra, lanza una excepción
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new NotFoundException("Categoría no encontrada"));
        CategoryDto categoryDto = entityDtoMapper.mapCategoryToDtoBasic(category); // Convierte la entidad a DTO
        return Response.builder() // Crea y retorna una respuesta con la categoría encontrada
                .status(200)
                .category(categoryDto)
                .build();
    }

    // Método para eliminar una categoría por su ID
    @Override
    public Response deleteCategory(Long categoryId) {
        // Buscar la categoría por ID, si no se encuentra, lanza una excepción
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new NotFoundException("Categoría no encontrada"));
        categoryRepo.delete(category); // Elimina la categoría de la base de datos
        return Response.builder() // Crea y retorna una respuesta con éxito
                .status(200)
                .message("Categoría eliminada")
                .build();
    }
}