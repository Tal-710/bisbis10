package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates the value for 'id' using the database's identity column.
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean isKosher;

    @Column(name = "total_ratings", precision = 10, scale = 2)
    private BigDecimal totalRatings = BigDecimal.ZERO;

    @Column(name = "number_of_ratings")
    private int numberOfRatings = 0;

    @Column(name = "average_rating", precision = 4, scale = 2) // Defines the precision and scale for decimal 'average_rating'.
    private BigDecimal averageRating = BigDecimal.ZERO;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    // Establishes a one-to-many relationship with Dish entities.
    // Cascade type ALL means any change to Restaurant will cascade to Dish (e.g., delete Restaurant will delete its Dishes).
    // Orphan removal true means deleting a Dish from the collection will delete it from the database.
    private Set<Dish> dishes = new HashSet<>();

    @OneToMany(mappedBy = "restaurant") // Establishes a one-to-many relationship with RestaurantCuisine entities.
    private Set<RestaurantCuisine> restaurantCuisines = new HashSet<>();

    // Constructors
    public Restaurant() {
        super();
    }

    public Restaurant(String name, boolean isKosher, BigDecimal averageRating) {
        super();
        this.name = name;
        this.isKosher = isKosher;
        this.averageRating = averageRating;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getIsKosher() {
        return isKosher;
    }

    public BigDecimal getTotalRatings() {
        return totalRatings;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public BigDecimal getRating() {
        return averageRating;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public Set<RestaurantCuisine> getRestaurantCuisines() {
        return restaurantCuisines;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsKosher(boolean isKosher) {
        this.isKosher = isKosher;
    }

    public void setRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    public void setTotalRatings(BigDecimal totalRatings) {
        this.totalRatings = totalRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public void setRestaurantCuisines(Set<RestaurantCuisine> restaurantCuisines) {
        this.restaurantCuisines = restaurantCuisines;
    }

    // Additional methods

    // Updates the restaurant rating with a new rating value.
    public void updateRating(BigDecimal newRating) {
        this.totalRatings = this.totalRatings.add(newRating); // Adds new rating to total.
        this.numberOfRatings++; // Increments the number of ratings.
        this.averageRating = this.totalRatings.divide(BigDecimal.valueOf(this.numberOfRatings), 2, BigDecimal.ROUND_HALF_UP); // Calculates new average rating.
    }
}
