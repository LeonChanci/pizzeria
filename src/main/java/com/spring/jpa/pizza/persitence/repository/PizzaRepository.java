package com.spring.jpa.pizza.persitence.repository;

import com.spring.jpa.pizza.persitence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

}
