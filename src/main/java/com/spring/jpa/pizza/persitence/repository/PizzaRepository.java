package com.spring.jpa.pizza.persitence.repository;

import com.spring.jpa.pizza.persitence.entity.PizzaEntity;
import com.spring.jpa.pizza.service.dto.UpdatePizzaPriceDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

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

    //@Query -> Usando SQL Nativo -> Usando Spring Expresión Lenguage
    //:#{#} -> Se usa para obtener el parametros del obtejo DTO y utilizarlos en la consulta
    //Se usa la anotación @Modifying en los @Query para insertar, actualzar o eliminar
    //Si no se usa @Modifying solo funcionará para Obtener (SELECT)
    @Modifying
    @Query(value =
            "UPDATE pizza " +
            "SET price = :#{#newPizzaPrice.newPrice} " +
            "WHERE id_pizza = :#{#newPizzaPrice.pizzaId}", nativeQuery = true)
    void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDTO newPizzaPrice);
}
