package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.DTO.OrderDTO;
import com.att.tdp.bisbis10.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping // Method to handle POST requests to create a new order.
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
        UUID newOrderId = orderService.createOrder(orderDTO);
        return ResponseEntity.ok().body(Map.of("orderId", newOrderId.toString()));
    }
}

