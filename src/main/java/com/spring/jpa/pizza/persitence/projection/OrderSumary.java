package com.spring.jpa.pizza.persitence.projection;

import java.time.LocalDateTime;

//Se crea la interface con los "métodos" a obtener de la consulta SQL nativa
public interface OrderSumary {
    Integer getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    Double getOrderTotal();
    String getPizzaNames();
}
