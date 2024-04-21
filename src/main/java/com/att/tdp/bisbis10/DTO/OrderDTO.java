package com.att.tdp.bisbis10.DTO;

import com.att.tdp.bisbis10.entity.OrderItem;

import java.util.Set;

public class OrderDTO {

    private Long restaurantId;
    private Set<OrderItem> orderItems;

    // Getters and setters
    public Long getRestaurantId() {
        return restaurantId;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}

