package com.spring.jpa.pizza.persitence.repository;

import com.spring.jpa.pizza.persitence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    //QueryMethods-> Obtener todas las pizzas activas ordenadas por precio
    List<PizzaEntity> findByAvailableTrueOrderByPrice();

    //QueryMethods-> Obtener una pizza activa por el nombre sin importar mayusculas o minusculas
    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);

    //QueryMethods-> Obtener todas las pizzas activas dónde la descripción contenga {description} sin importar mayusculas o minusculas
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    //QueryMethods-> Obtener todas las pizzas activas dónde la descripción NO contenga {description} sin importar mayusculas o minusculas
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    //QueryMethods-> Obtener cuantas pizzas Veganas hay en BD
    int countByVeganTrue();
}
