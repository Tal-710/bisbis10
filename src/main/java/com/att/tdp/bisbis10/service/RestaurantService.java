package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.entity.Cuisine;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.entity.RestaurantCuisine;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        // Retrieves all restaurant entries from the database.
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(Long id) {
        // Retrieves a restaurant by its ID. Returns an Optional, which can be empty if no restaurant is found.
        return restaurantRepository.findById(id);
    }

    public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
        // Fetches a list of restaurants that offer a specific cuisine.
        return restaurantRepository.findRestaurantsByCuisineName(cuisine);
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        // Saves the provided Restaurant object to the database and returns the persisted entity.
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Long id, Restaurant restaurantDetails, List<String> cuisineNames) {
        // Updates restaurant details if the restaurant is found, or throws an exception if not.
        return restaurantRepository.findById(id).map(restaurant -> {
            restaurant.setName(restaurantDetails.getName());
            restaurant.setIsKosher(restaurantDetails.getIsKosher());
            restaurant.setRating(restaurantDetails.getRating());

            Set<RestaurantCuisine> restaurantCuisines = new HashSet<>();
            for (String cuisineName : cuisineNames) {
                // Create and populate a RestaurantCuisine object for each cuisine name.
                RestaurantCuisine restaurantCuisine = new RestaurantCuisine();
                Cuisine cuisine = new Cuisine(cuisineName);

                restaurantCuisine.setRestaurant(restaurant);
                restaurantCuisine.setCuisine(cuisine);
                restaurantCuisines.add(restaurantCuisine);
            }

            // Set the updated collection of RestaurantCuisines to the restaurant.
            restaurant.setRestaurantCuisines(restaurantCuisines);
            return restaurantRepository.save(restaurant); // Saves the updated restaurant.
        }).orElseThrow(() -> new RuntimeException("Restaurant not found with id " + id));
    }

    public void deleteRestaurant(Long id) {
        // Deletes a restaurant by its ID.
        restaurantRepository.deleteById(id);
    }

    @Transactional // Marks the method to be transactional to ensure consistency and rollback on failure.
    public Restaurant updateRestaurantRating(Long restaurantId, Double rating) {
        // Updates the rating of a specific restaurant.
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
        restaurant.setRating(BigDecimal.valueOf(rating));
        return restaurantRepository.save(restaurant); // Saves the updated rating and returns the updated restaurant.
    }
}
