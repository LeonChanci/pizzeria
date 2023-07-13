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

    //--------------GET-------------------//
    public List<PizzaEntity> findAll(){
        //Llamar al jdbcTemplate -> permite crear consultas SQL desde java y convertir
        //El resultado en clases Java
        return this.pizzaRepository.findAll();
    }

    public PizzaEntity get(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public List<PizzaEntity> getAvailable(){
        return this.pizzaRepository.findByAvailableTrueOrderByPrice();
    }

    public PizzaEntity getByName(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

    public List<PizzaEntity> getWith(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public int getCountPizzasVegan(){
        return this.pizzaRepository.countByVeganTrue();
    }

    //--------------POST-------------------//
    public PizzaEntity save(PizzaEntity pizzaEntity){
        return pizzaRepository.save(pizzaEntity);
    }

    //--------------DELETE-------------------//
    public void delete(int idPizza){
        pizzaRepository.deleteById(idPizza);
    }

    //--------------VALIDATIONS-------------------//
    public boolean exist(int idPizza){
        return pizzaRepository.existsById(idPizza);
    }
}
