package com.att.tdp.bisbis10.entity;

import java.io.Serializable;
import java.util.Objects;


 //Represents a composite key for linking restaurants and cuisines.
//This class allows us to uniquely identify a relationship between a restaurant and a cuisine.

public class RestaurantCuisineId implements Serializable {

    private Long restaurantId; // ID of the restaurant part of the key.
    private Long cuisineId; // ID of the cuisine part of the key.

    public RestaurantCuisineId() {
        super();
    }


//      Checks if this RestaurantCuisineId is equal to another object.
//      Two RestaurantCuisineIds are considered equal if they have the same restaurantId and cuisineId.
//
//      @param o Object to be compared for equality with this RestaurantCuisineId.
//      @return true if the specified object is equal to this RestaurantCuisineId; false otherwise.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // The same instance check.
        if (o == null || getClass() != o.getClass()) return false; // Checks if null or different classes.

        RestaurantCuisineId that = (RestaurantCuisineId) o; // Cast the object for comparison.
        return Objects.equals(restaurantId, that.restaurantId) && Objects.equals(cuisineId, that.cuisineId);
    }


//      Returns a hash code value for this RestaurantCuisineId.
//     A consistent hash code is important because it helps keep the collection operations fast and reliable.
//
//      @return hash code value for this RestaurantCuisineId.

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, cuisineId); // Calculates hash code based on restaurantId and cuisineId.
    }
}
