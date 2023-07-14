package com.spring.jpa.pizza.persitence.repository;

import com.spring.jpa.pizza.persitence.entity.PizzaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.ListPagingAndSortingRepository;

//ListPagingAndSortingRepository -> Páginar las listas o las consultas de la BD
public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Integer> {

    //Obtener todas las pizzas activas
    Page<PizzaEntity> findByAvailableTrue(PageRequest pageable);
}
