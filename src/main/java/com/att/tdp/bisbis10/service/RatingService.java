package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.entity.Restaurant;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;

@Service // Marks this class as a service component within the Spring framework, indicating it contains business logic.
public class RatingService {

    @Transactional // Ensures that the method is executed within a transactional context, which is crucial for maintaining data consistency.
    public Restaurant updateRestaurantRating(Restaurant restaurant, Double rating) {
        // Converts the double rating to BigDecimal and updates the restaurant's rating.
        // BigDecimal is used for precise arithmetic operations, particularly important for financial calculations or ratings.
        restaurant.updateRating(BigDecimal.valueOf(rating));

        // Returns the restaurant entity after updating its rating.
        return restaurant;
    }
}
