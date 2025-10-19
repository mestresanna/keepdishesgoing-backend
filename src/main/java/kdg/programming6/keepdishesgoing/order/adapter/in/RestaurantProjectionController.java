package kdg.programming6.keepdishesgoing.order.adapter.in;

import kdg.programming6.keepdishesgoing.order.adapter.in.dto.RestaurantMapperDto;
import kdg.programming6.keepdishesgoing.order.adapter.in.dto.RestaurantProjectionDto;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.FindAllRestaurantsProjectionsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders/restaurants")
public class RestaurantProjectionController {

    private final FindAllRestaurantsProjectionsUseCase findAllRestaurants;
    private final RestaurantMapperDto restaurantMapper;

    public RestaurantProjectionController(FindAllRestaurantsProjectionsUseCase findAllRestaurants, RestaurantMapperDto restaurantMapper) {
        this.findAllRestaurants = findAllRestaurants;
        this.restaurantMapper = restaurantMapper;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantProjectionDto>> getAllRestaurants() {
        List<RestaurantProjectionDto> restaurants = this.findAllRestaurants.findAllRestaurants().stream()
                .map(restaurantMapper::toDto)
                .toList();
        return ResponseEntity.ok(restaurants);
    }
}
