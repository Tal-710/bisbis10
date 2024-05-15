package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping // Method to handle GET requests for all restaurants.
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        // Returns a list of all restaurants.
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/{id:[\\d]+}") // Matches only numeric path variables
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        // Returns the restaurant if found, or an HTTP 404 if not.
        return restaurantService.getRestaurantById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{cuisine:[^\\d]+}") // Matches only non-numeric path variables
    public ResponseEntity<List<Restaurant>> getRestaurantsByCuisine(@PathVariable String cuisine) {
        return ResponseEntity.ok(restaurantService.getRestaurantsByCuisine(cuisine));
    }

    @PostMapping // Method to handle POST requests to create a new restaurant.
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        return ResponseEntity.ok(restaurantService.saveRestaurant(restaurant));
    }

    @PutMapping("/{id}") // Method to handle PUT requests to update an existing restaurant.
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant, @RequestParam List<String> cuisineNames) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(id, restaurant, cuisineNames));
    }

    @DeleteMapping("/{id}") // Method to handle DELETE requests to remove a restaurant.
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
    }
}
