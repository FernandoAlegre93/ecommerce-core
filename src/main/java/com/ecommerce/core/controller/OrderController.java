package com.ecommerce.core.controller;


import com.ecommerce.core.model.OrderDetailEntity;
import com.ecommerce.core.model.OrderEntity;
import com.ecommerce.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        List<OrderEntity> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<OrderEntity> createOrder(@PathVariable Long userId, @RequestBody List<OrderDetailEntity> orderDetails) {
        try {
            OrderEntity newOrder = orderService.createOrder(userId, orderDetails);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Manejo básico de excepciones. En un proyecto real, esto sería más robusto.
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}