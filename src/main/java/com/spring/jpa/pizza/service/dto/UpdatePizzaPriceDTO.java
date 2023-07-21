package com.spring.jpa.pizza.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

//Se crea el DTO para actualizar el campo price del objeto Pizza
public class UpdatePizzaPriceDTO {
    private int pizzaId;
    private Double newPrice;
}
