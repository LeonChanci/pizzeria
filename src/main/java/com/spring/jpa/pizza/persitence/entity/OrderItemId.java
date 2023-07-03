package com.spring.jpa.pizza.persitence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class OrderItemId implements Serializable {

    @Column(name = "id_order")
    private Integer idOrder;

    @Column(name = "id_item")
    private Integer idItem;
}
