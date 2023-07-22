package com.spring.jpa.pizza.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RandomOrderDTO {
    private String idCustomer;
    private String method;
}
