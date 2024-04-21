//RestaurantCuisine serves as a join table entity in a database schema to manage a many-to-many relationship between Restaurant and Cuisine
package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;

@Entity // Marks this class as a JPA entity.
@Table(name = "restaurant_cuisines")
@IdClass(RestaurantCuisineId.class) // Specifies the class that is used for the composite key.
public class RestaurantCuisine {

    @Id // Marks this field as part of the primary key in the database.
    @Column(name = "restaurant_id") // Maps this field to the restaurant_id column in the restaurant_cuisines table.
    private Long restaurantId;

    @Id // Marks this field as part of the primary key in the database.
    @Column(name = "cuisine_id") // Maps this field to the cuisine_id column in the restaurant_cuisines table.
    private Long cuisineId;

    @ManyToOne // Establishes a many-to-one relationship from RestaurantCuisine to Restaurant.
    @JoinColumn(name = "restaurant_id", insertable = false, updatable = false) // Connects the restaurant field to the restaurant_id column in the database.
    private Restaurant restaurant;

    @ManyToOne // Establishes a many-to-one relationship from RestaurantCuisine to Cuisine.
    @JoinColumn(name = "cuisine_id", insertable = false, updatable = false) // Connects the cuisine field to the cuisine_id column in the database.
    private Cuisine cuisine;

    // Constructor
    public RestaurantCuisine() {
        super();
    }

    // Getter methods
    public Long getRestaurantId() {
        return restaurantId;
    }

    public Long getCuisineId() {
        return cuisineId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    // Setter methods
    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setCuisineId(Long cuisineId) {
        this.cuisineId = cuisineId;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

}
