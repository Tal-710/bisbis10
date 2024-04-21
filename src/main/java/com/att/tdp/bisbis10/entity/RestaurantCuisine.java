package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant_cuisines")
public class RestaurantCuisine {

    @Id
    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Id
    @Column(name = "cuisine_id")
    private Long cuisineId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", insertable = false, updatable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuisine_id", insertable = false, updatable = false)
    private Cuisine cuisine;

    // Constructors
    public RestaurantCuisine() {
        super();
    }

    public RestaurantCuisine(Restaurant restaurant, Cuisine cuisine) {
        this.restaurant = restaurant;
        this.cuisine = cuisine;
        this.restaurantId = restaurant.getId();  // Ensure the restaurant ID is set
        this.cuisineId = cuisine.getId();        // Ensure the cuisine ID is set
    }

    // Getters and Setters
    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(Long cuisineId) {
        this.cuisineId = cuisineId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.restaurantId = restaurant != null ? restaurant.getId() : null;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
        this.cuisineId = cuisine != null ? cuisine.getId() : null;
    }
}
