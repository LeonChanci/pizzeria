package com.spring.jpa.pizza.persitence.repository;

import com.spring.jpa.pizza.persitence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
}
