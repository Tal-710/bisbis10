package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service // Marks this class as a service component in Spring, which holds business logic and calls methods in the repository layer.
public class DishService {

    private final DishRepository dishRepository; // Dependency injection of the DishRepository interface.

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository; // Assigns the injected DishRepository to the local variable.
    }

    public Dish saveDish(Dish dish) {
        // Saves a given dish to the database using DishRepository and returns the saved dish.
        return dishRepository.save(dish);
    }

    public Optional<Dish> getDishById(Long id) {
        // Retrieves a dish by its ID using DishRepository. Returns an Optional, which can be empty if no dish is found.
        return dishRepository.findById(id);
    }

    public List<Dish> getAllDishesByRestaurant(Long restaurantId) {
        return dishRepository.findAllByRestaurantId(restaurantId);
    }

    public void deleteDish(Long id) {
        // Deletes the dish with the specified ID using DishRepository.
        dishRepository.deleteById(id);
    }

    public Dish updateDish(Long id, Dish updatedDish) {
        // Updates a dish with new information. If the dish is found, it updates the fields and saves the changes.
        return dishRepository.findById(id).map(dish -> {
            dish.setName(updatedDish.getName());
            dish.setDescription(updatedDish.getDescription());
            dish.setPrice(updatedDish.getPrice());
            dish.setRestaurant(updatedDish.getRestaurant());
            return dishRepository.save(dish); // Saves the updated dish back to the database.
        }).orElseThrow(() -> new RuntimeException("Dish not found with id " + id)); // Throws an exception if no dish is found with the given ID.
    }
}
