package kdg.programming6.keepdishesgoing.order.adapter.in;

import kdg.programming6.keepdishesgoing.order.adapter.in.dto.*;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.FindAllRestaurantsProjectionsUseCase;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.FindRestaurantDetailsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders/restaurants")
public class RestaurantProjectionController {

    private final FindAllRestaurantsProjectionsUseCase findAllRestaurants;
    private final FindRestaurantDetailsUseCase findRestaurantDetails;
    private final RestaurantMapperDto restaurantMapper;
    private final MenuProjectionMapperDto menuMapper;

    public RestaurantProjectionController(FindAllRestaurantsProjectionsUseCase findAllRestaurants, FindRestaurantDetailsUseCase findRestaurantDetails, RestaurantMapperDto restaurantMapper, MenuProjectionMapperDto menuMapper) {
        this.findAllRestaurants = findAllRestaurants;
        this.findRestaurantDetails = findRestaurantDetails;
        this.restaurantMapper = restaurantMapper;
        this.menuMapper = menuMapper;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantProjectionDto>> getAllRestaurants() {
        List<RestaurantProjectionDto> restaurants = this.findAllRestaurants.findAllRestaurants().stream()
                .map(restaurantMapper::toDto)
                .toList();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDetailsDto> getRestaurantDetails(@PathVariable UUID restaurantId) {
        var restaurantProjection = findRestaurantDetails.findRestaurantDetails(restaurantId);
        var menus = findRestaurantDetails.findAllMenusForRestaurant(restaurantId);
        var menuDtos = menus.stream().map(menuMapper::toDto).toList();

        return ResponseEntity.ok(restaurantMapper.toDetailsDto(restaurantProjection, menuDtos));
    }
}
