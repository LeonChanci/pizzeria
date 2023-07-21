package com.spring.jpa.pizza.service;

import com.spring.jpa.pizza.persitence.entity.OrderEntity;
import com.spring.jpa.pizza.persitence.projection.OrderSumary;
import com.spring.jpa.pizza.persitence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    public static final String DOMICILIO = "D";
    public static final String LLEVAR = "C";
    public static final String LUGAR = "S";

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll(){
        return this.orderRepository.findAll();
    }

    public List<OrderEntity> getTodayOrders(){
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.orderRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity> getByAfterDate(LocalDateTime date){
        return this.orderRepository.findAllByDateAfter(date);
    }
    public List<OrderEntity> getOutsideOrders(){
        List<String> methods = Arrays.asList(DOMICILIO, LLEVAR);
        return this.orderRepository.findAllByMethodIn(methods);
    }
    public List<OrderEntity> getByMethods(String methods){
        String[] methodsArray = methods.split("-");
        return this.orderRepository.findAllByMethodIn(Arrays.stream(methodsArray).toList());
    }
    public List<OrderEntity> getCustomerOrders(String idCustomer){
        return this.orderRepository.findCustomerOrders(idCustomer);
    }

    public OrderSumary getSumary(int orderId){
        return this.orderRepository.findSumary(orderId);
    }
}
