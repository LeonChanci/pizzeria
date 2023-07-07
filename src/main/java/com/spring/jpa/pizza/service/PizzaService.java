package com.spring.jpa.pizza.service;

import com.spring.jpa.pizza.persitence.entity.PizzaEntity;
import com.spring.jpa.pizza.persitence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    //Como esta como final se agrega como contructor de parámetros
    @Autowired
    public PizzaService(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> findAll(){
        //Llamar al jdbcTemplate -> permite crear consultas SQL desde java y convertir
        //El resultado en clases Java
        return this.pizzaRepository.findAll();
    }

    public PizzaEntity get(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }
}
