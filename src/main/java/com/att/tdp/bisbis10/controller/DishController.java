package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/dishes")
public class DishController {

    private final DishService dishService; // Declare a service that will handle all dish operations.

    @Autowired // Automatically injects the necessary dishService when DishController is created.
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping // Annotation for mapping HTTP POST requests onto specific handler methods.
    public ResponseEntity<Dish> createDish(@PathVariable Long restaurantId, @RequestBody Dish dish) {
        Dish createdDish = dishService.saveDish(dish);
        return ResponseEntity.status(201).body(createdDish);
    }

    @GetMapping("/{dishId}") // Maps HTTP GET requests to fetch a dish by its ID.
    public ResponseEntity<Dish> getDishById(@PathVariable Long restaurantId, @PathVariable Long dishId) {
        return dishService.getDishById(dishId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/restaurants/{restaurantId}/dishes") // Maps HTTP GET requests to fetch all dishes in a restaurant.
    public ResponseEntity<List<Dish>> getAllDishes(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(dishService.getAllDishesByRestaurant(restaurantId));
    }

    @PutMapping("/{dishId}") // Maps HTTP PUT requests to update an existing dish.
    public ResponseEntity<Dish> updateDish(@PathVariable Long restaurantId, @PathVariable Long dishId, @RequestBody Dish dish) {
        return ResponseEntity.ok(dishService.updateDish(dishId, dish));
    }

    @DeleteMapping("/{dishId}") // Maps HTTP DELETE requests to delete a dish by its ID.
    public ResponseEntity<Void> deleteDish(@PathVariable Long restaurantId, @PathVariable Long dishId) {
        dishService.deleteDish(dishId);
        return ResponseEntity.noContent().build();
    }
}
