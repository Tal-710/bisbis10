package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class RatingService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant updateRestaurantRating(Restaurant restaurant, Double rating) {
        // Validation inside the service
        if (rating == null || rating < 0 || rating > 10) {
            throw new IllegalArgumentException("Invalid rating: " + rating);
        }

        // Assuming updateRating is a method within the Restaurant entity that sets the new rating
        restaurant.updateRating(BigDecimal.valueOf(rating));

        // Save and return the updated restaurant
        return restaurantRepository.save(restaurant);
    }
}


