package com.spring.jpa.pizza.persitence.repository;

import com.spring.jpa.pizza.persitence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    //QueryMethods-> Obtener todas las pizzas activas ordenadas por precio
    List<PizzaEntity> findByAvailableTrueOrderByPrice();

    //QueryMethods-> Obtener solamente una pizza activa por el nombre sin importar mayusculas o minusculas
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);

    //QueryMethods-> Obtener todas las pizzas activas dónde la descripción contenga {description} sin importar mayusculas o minusculas
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    //QueryMethods-> Obtener todas las pizzas activas dónde la descripción NO contenga {description} sin importar mayusculas o minusculas
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    //QueryMethods-> Obtener las 3 primeras pizzas dónde el precio sea menor o igual a un parámetro específico
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceDesc(double price);

    //QueryMethods-> Obtener cuantas pizzas Veganas hay en BD
    int countByVeganTrue();
}
