package com.att.tdp.bisbis10;

import com.att.tdp.bisbis10.controller.DishController;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.DishRepository;
import com.att.tdp.bisbis10.service.DishService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Use Mockito's JUnit extension to initialize mocks and inject them.
public class DishControllerTest {

    @Mock // Creates a mock instance of DishRepository for use within this test class.
    private DishRepository dishRepository;

    @InjectMocks
    private DishService dishService;

    private MockMvc mockMvc; // MockMvc provides support for Spring MVC testing.

    @Test
    public void testAddDish() throws Exception {
        // Setup test data and expectations
        Restaurant restaurant = new Restaurant("Test Restaurant", true, BigDecimal.valueOf(4.5));
        Dish dish = new Dish("Shakshuka", "Great one", BigDecimal.valueOf(34), restaurant);

        when(dishRepository.save(any())).thenReturn(dish); // Configure the mock to return a dish when save is called.

        mockMvc = MockMvcBuilders.standaloneSetup(new DishController(dishService)).build(); // Initialize MockMvc with the DishController.

        // Simulate POST request to add a new dish and verify the response
        mockMvc.perform(MockMvcRequestBuilders.post("/restaurants/1/dishes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Shakshuka\",\"description\":\"Great one\",\"price\":34}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdateDish() throws Exception {
        // Setup data for the update test
        Dish dish = new Dish("Shakshuka", "Great one", BigDecimal.valueOf(34), null);
        Optional<Dish> optionalDish = Optional.of(dish);

        when(dishRepository.findById(1L)).thenReturn(optionalDish); // Stub findById to return an Optional containing the dish.
        when(dishRepository.save(any())).thenReturn(dish); // Stub save to simply return the dish.

        mockMvc = MockMvcBuilders.standaloneSetup(new DishController(dishService)).build(); // Setup MockMvc.

        // Simulate PUT request to update the dish and verify the response
        mockMvc.perform(MockMvcRequestBuilders.put("/restaurants/1/dishes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Great one\",\"price\":34}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteDish() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new DishController(dishService)).build(); // Setup MockMvc.

        // Simulate DELETE request and expect no content response
        mockMvc.perform(MockMvcRequestBuilders.delete("/restaurants/1/dishes/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGetDishesByRestaurant() throws Exception {
        // Setup dishes for the test
        Dish dish1 = new Dish("Humus", "Good one", BigDecimal.valueOf(48), null);
        Dish dish2 = new Dish("Falafel", "Delicious", BigDecimal.valueOf(30), null);
        List<Dish> dishes = Arrays.asList(dish1, dish2);

        lenient().when(dishRepository.findAllByRestaurantId(1L)).thenReturn(dishes); // Use lenient to avoid stubbing issues.

        mockMvc = MockMvcBuilders.standaloneSetup(new DishController(dishService)).build();

        // Simulate GET request to retrieve dishes by restaurant and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/restaurants/1/dishes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
