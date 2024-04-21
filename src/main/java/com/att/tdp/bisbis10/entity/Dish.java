package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "dishes") // Specifies the name of the table in the database for this entity.
public class Dish {
    @Id // Marks the 'id' field as the primary key of the table.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures how the primary key is generated.
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne // Defines a many-to-one relationship with the Restaurant entity.
    @JoinColumn(name = "restaurant_id", nullable = false) // Specifies the foreign key column.
    private Restaurant restaurant; // Links this dish to a specific restaurant.

    // Constructors, getters, and setters
    public Dish() {
        super();
    }

    // Constructor that initializes a new Dish with all fields.
    public Dish(String name, String description, BigDecimal price, Restaurant restaurant) {
        super();
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
