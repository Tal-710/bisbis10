package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    @Id // Marks 'id' as the primary key of the entity.
    @GeneratedValue // Configures the generation strategy for the primary key.
    private UUID id; // Uses UUID for the unique identifier of the order.

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // Defines a one-to-many relationship with OrderItem entities.
    private Set<OrderItem> orderItems; // Contains all items that are part of the order.

    // Default constructor
    public Order() {
        super();
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
        for (OrderItem item : orderItems) {
            item.setOrder(this); // Ensures each order item is linked back to this order.
        }
    }

}
