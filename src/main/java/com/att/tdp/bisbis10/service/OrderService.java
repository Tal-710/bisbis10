package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.DTO.OrderDTO;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public UUID createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setRestaurantId(orderDTO.getRestaurantId());

        // Using a local variable to ensure it is effectively final for lambda usage
        final Order finalOrder = order;

        // Convert OrderItemDTOs to OrderItem entities and associate them with the Order
        Set<OrderItem> orderItems = orderDTO.getOrderItems().stream()
                .map(dto -> createOrderItem(dto, finalOrder))
                .collect(Collectors.toSet());

        order.setOrderItems(orderItems); // Set the converted OrderItems to the Order
        order = orderRepository.save(order); // Save the Order, cascading saves the OrderItems

        return order.getId(); // Return the UUID of the created Order
    }

    private OrderItem createOrderItem(OrderItem dto, Order order) {
        OrderItem orderItem = new OrderItem();
        orderItem.setDishId(dto.getDishId());
        orderItem.setAmount(dto.getAmount());
        orderItem.setOrder(order); // Set the order here, ensuring it is linked correctly
        return orderItem;
    }
}
