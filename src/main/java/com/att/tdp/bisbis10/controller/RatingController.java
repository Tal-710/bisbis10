package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.DTO.RatingDTO;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.att.tdp.bisbis10.repository.RestaurantRepository;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RatingService ratingService;

    @PostMapping // Method to handle POST requests, which add a new rating.
    public ResponseEntity<?> addRestaurantRating(@RequestBody RatingDTO ratingDTO) {
        // Checks if the rating is valid.
        if (ratingDTO.getRating() == null || ratingDTO.getRating() < 0 || ratingDTO.getRating() > 10) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid rating");
        }

        // Retrieves the restaurant based on the ID. If not found, throws an exception.
        Restaurant restaurant = restaurantRepository.findById(ratingDTO.getRestaurantId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));

        // Updates the rating of the restaurant.
        Restaurant updatedRestaurant = ratingService.updateRestaurantRating(restaurant, ratingDTO.getRating());

        // Saves the updated restaurant details.
        restaurantRepository.save(updatedRestaurant);

        // Returns the updated restaurant details with an OK status.
        return ResponseEntity.ok(updatedRestaurant);
    }
}

