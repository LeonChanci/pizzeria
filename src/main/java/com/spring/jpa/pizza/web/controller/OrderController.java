package com.spring.jpa.pizza.web.controller;

import com.spring.jpa.pizza.persitence.entity.OrderEntity;
import com.spring.jpa.pizza.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll(){
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders(){
        return ResponseEntity.ok(orderService.getTodayOrders());
    }

    @GetMapping("/afterDate/{date}")
    public ResponseEntity<List<OrderEntity>> getByAfterDate(@PathVariable LocalDateTime date){
        return ResponseEntity.ok(orderService.getByAfterDate(date));
    }

    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getByOutsideOrders(){
        return ResponseEntity.ok(orderService.getOutsideOrders());
    }

    @GetMapping("/filterMethods/{methods}")
    public ResponseEntity<List<OrderEntity>> getByMethods(@PathVariable String methods){
        return ResponseEntity.ok(orderService.getByMethods(methods));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getByIdCustomer(@PathVariable String id){
        return ResponseEntity.ok(orderService.getCustomerOrders(id));
    }
}
