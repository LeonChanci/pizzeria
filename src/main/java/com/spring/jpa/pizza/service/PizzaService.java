package com.spring.jpa.pizza.service;

import com.spring.jpa.pizza.persitence.entity.PizzaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final JdbcTemplate jdbcTemplate;

    //Como esta como final se agrega como contructor de parámetros
    @Autowired
    public PizzaService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PizzaEntity> getAll(){
        //Llamar al jdbcTemplate -> permite crear consultas SQL desde java y convertir
        //El resultado en clases Java
        return this.jdbcTemplate.query("SELECT * FROM pizza", new BeanPropertyRowMapper<>(PizzaEntity.class));
    }
}
