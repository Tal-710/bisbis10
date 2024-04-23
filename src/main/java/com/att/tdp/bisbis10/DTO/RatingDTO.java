package com.att.tdp.bisbis10.DTO;

import com.att.tdp.bisbis10.entity.Restaurant;

public class RatingDTO {
    private Long restaurantId;  // Correctly typed as Long
    private Double rating;

    public RatingDTO(long restaurantId, double rating) {
        super();
        this.restaurantId = restaurantId;  // Ensure the fields are initialized
        this.rating = rating;
    }

    // Getters and Setters
    public Long getRestaurantId() { // Corrected to return Long
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) { // Setter is correctly typed
        this.restaurantId = restaurantId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}

