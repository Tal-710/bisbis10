package com.att.tdp.bisbis10;

import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import com.att.tdp.bisbis10.service.RestaurantService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc // Configures MockMvc for testing Spring MVC controllers without starting a full HTTP server.
class Bis10ApplicationTests {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired // Injects the MockMvc to simulate HTTP requests and responses.
	private MockMvc mockMvc;

	@MockBean // Creates a mock implementation for the RestaurantService to be used in testing.
	private RestaurantService restaurantService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	public void testFindByCuisine() {
		// Tests if restaurants can be retrieved by their cuisine type correctly.
		String cuisineName = "Indian";
		List<Restaurant> restaurants = restaurantRepository.findRestaurantsByCuisineName(cuisineName);
		assertFalse(restaurants.isEmpty()); // Ensures that the result is not empty.
		assertTrue(restaurants.stream().anyMatch(restaurant -> restaurant.getName().equals("Taizu"))); // Checks if any restaurant matches the expected name.
	}

	@Test
	void createOrderTest() throws Exception {
		// Simulates the creation of an order using a POST request.
		var orderItems = List.of(
				Map.of("dishId", 1, "amount", 3)
		);
		var requestBody = Map.of(
				"restaurantId", 1,
				"orderItems", orderItems
		);

		// Converts the request body to a JSON string for the HTTP request.
		String requestBodyJson = objectMapper.writeValueAsString(requestBody);

		// Simulates sending a POST request to the "/order" endpoint.
		mockMvc.perform(post("/order")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBodyJson))
				.andExpect(status().isOk());
	}

	@Test
	public void testUpdateRestaurant() throws Exception {
		// Tests updating a restaurant's information using a PUT request.
		Long restaurantId = 1L;
		String requestBody = "{\"name\": \"Taizu\", \"isKosher\": true, \"averageRating\": \"4.5\"}";
		String cuisineQueryString = "cuisineNames=Asian";

		// Prepares a mock restaurant to return when the service method is called.
		Restaurant updatedRestaurant = new Restaurant();
		updatedRestaurant.setName("Taizu");
		updatedRestaurant.setIsKosher(true);
		updatedRestaurant.setRating(new BigDecimal("4.5"));

		// Configures the mock to return a specific object when the updateRestaurant method is invoked.
		when(restaurantService.updateRestaurant(eq(restaurantId), any(Restaurant.class), anyList()))
				.thenReturn(updatedRestaurant);

		// Simulates sending a PUT request to the "/restaurants/{id}" endpoint.
		mockMvc.perform(put("/restaurants/" + restaurantId + "?" + cuisineQueryString)
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isOk());

		// Verifies that the updateRestaurant method was called on the mock service.
		verify(restaurantService).updateRestaurant(eq(restaurantId), any(Restaurant.class), anyList());
	}
}
