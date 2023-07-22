package com.spring.jpa.pizza.web.controller;

import com.spring.jpa.pizza.persitence.entity.OrderEntity;
import com.spring.jpa.pizza.persitence.projection.OrderSumary;
import com.spring.jpa.pizza.service.OrderService;
import com.spring.jpa.pizza.service.dto.RandomOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(this.orderService.getTodayOrders());
    }

    @GetMapping("/afterDate/{date}")
    public ResponseEntity<List<OrderEntity>> getByAfterDate(@PathVariable LocalDateTime date){
        return ResponseEntity.ok(this.orderService.getByAfterDate(date));
    }

    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getByOutsideOrders(){
        return ResponseEntity.ok(this.orderService.getOutsideOrders());
    }

    @GetMapping("/filterMethods/{methods}")
    public ResponseEntity<List<OrderEntity>> getByMethods(@PathVariable String methods){
        return ResponseEntity.ok(this.orderService.getByMethods(methods));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getByIdCustomer(@PathVariable String id){
        return ResponseEntity.ok(this.orderService.getCustomerOrders(id));
    }

    @GetMapping("/sumary/{id}")
    public ResponseEntity<OrderSumary> getSumary(@PathVariable int id){
        return ResponseEntity.ok(this.orderService.getSumary(id));
    }

    @PostMapping("/random")
    public ResponseEntity<Boolean> randomOrder(@RequestBody RandomOrderDTO dto){
        return ResponseEntity.ok(this.orderService.saveRandomOrder(dto));
    }
}
