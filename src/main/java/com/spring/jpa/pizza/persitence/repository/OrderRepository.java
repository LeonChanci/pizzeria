package com.spring.jpa.pizza.persitence.repository;

import com.spring.jpa.pizza.persitence.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

    //QueryMethods-> Obtener todas las ordenes después de una fecha especifica
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);

    //QueryMethods-> Obtener todas las ordenes dónde los métodos de orden sean los específicos.
    List<OrderEntity> findAllByMethodIn(List<String> methods);

    // @Query -> usando SQL Nativo (nativeQuery = true) Obtener todas las ordenes por medio del id del cliente
    @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") String idCustomer);
}
