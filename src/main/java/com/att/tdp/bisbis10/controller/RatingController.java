package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.DTO.RatingDTO;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.service.RatingService;
import com.att.tdp.bisbis10.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private RestaurantService restaurantService; // Handles Restaurant operations

    @PostMapping
    public ResponseEntity<?> addRestaurantRating(@RequestBody RatingDTO ratingDTO) {
        // Fetch the restaurant by ID before updating the rating
        Restaurant restaurant = restaurantService.getRestaurantById(ratingDTO.getRestaurantId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));

        // Delegate the update and validation to the service layer
        Restaurant updatedRestaurant = ratingService.updateRestaurantRating(restaurant, ratingDTO.getRating());

        return ResponseEntity.ok(updatedRestaurant);
    }
}
