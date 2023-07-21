package com.spring.jpa.pizza.persitence.repository;

import com.spring.jpa.pizza.persitence.entity.OrderEntity;
import com.spring.jpa.pizza.persitence.projection.OrderSumary;
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

    // @Query ->  usando SQL Nativo PROYECCIÓN
    @Query(value =
            "SELECT po.id_order AS idOrder, cu.name AS customerName, po.date AS orderDate, " +
            "        po.total AS orderTotal, GROUP_CONCAT(pi.name) AS pizzaNames " +
            "FROM pizza_order po " +
            "         INNER JOIN customer cu ON po.id_customer = cu.id_customer " +
            "         INNER JOIN order_item oi ON po.id_order = oi.id_order " +
            "         INNER JOIN pizza pi ON oi.id_pizza = pi.id_pizza " +
            "WHERE po.id_order = :orderId " +
            "GROUP BY po.id_order, cu.name, po.date, po.total", nativeQuery = true)
    OrderSumary findSumary(@Param("orderId") int orderId);
}
