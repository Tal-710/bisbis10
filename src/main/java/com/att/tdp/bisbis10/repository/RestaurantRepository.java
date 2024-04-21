package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // Custom query to find restaurants based on the name of the cuisine they serve.
    // This method leverages Query to perform a complex join across Restaurant, RestaurantCuisine, and Cuisine entities.
    @Query("SELECT r FROM Restaurant r JOIN r.restaurantCuisines rc JOIN rc.cuisine c WHERE c.name = :cuisineName")
    List<Restaurant> findRestaurantsByCuisineName(String cuisineName);

}
