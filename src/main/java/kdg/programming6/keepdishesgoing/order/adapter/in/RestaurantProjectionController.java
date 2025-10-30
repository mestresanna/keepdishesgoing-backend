package kdg.programming6.keepdishesgoing.order.adapter.in;

import kdg.programming6.keepdishesgoing.order.adapter.in.response.menu.MenuProjectionMapperDto;
import kdg.programming6.keepdishesgoing.order.adapter.in.response.restaurant.RestaurantDetailsDto;
import kdg.programming6.keepdishesgoing.order.adapter.in.response.restaurant.RestaurantProjectionDto;
import kdg.programming6.keepdishesgoing.order.adapter.in.response.restaurant.RestaurantProjectionMapperDto;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.FilterRestaurantsUseCase;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.FindAllRestaurantsProjectionsUseCase;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.FindRestaurantDetailsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders/restaurants")
public class RestaurantProjectionController {

    private final FindAllRestaurantsProjectionsUseCase findAllRestaurants;
    private final FindRestaurantDetailsUseCase findRestaurantDetails;
    private final FilterRestaurantsUseCase filterRestaurants;
    private final RestaurantProjectionMapperDto restaurantMapper;
    private final MenuProjectionMapperDto menuMapper;

    public RestaurantProjectionController(FindAllRestaurantsProjectionsUseCase findAllRestaurants, FindRestaurantDetailsUseCase findRestaurantDetails, RestaurantProjectionMapperDto restaurantMapper, MenuProjectionMapperDto menuMapper, FilterRestaurantsUseCase filterRestaurants) {
        this.findAllRestaurants = findAllRestaurants;
        this.findRestaurantDetails = findRestaurantDetails;
        this.restaurantMapper = restaurantMapper;
        this.menuMapper = menuMapper;
        this.filterRestaurants = filterRestaurants;
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

    @GetMapping("/filter")
    public ResponseEntity<List<RestaurantProjectionDto>> filterRestaurants(
            @RequestParam(required = false) String cuisine,
            @RequestParam(required = false) String priceRange
    ) {
        var restaurants = filterRestaurants.filterRestaurants(cuisine, priceRange).stream()
                .map(restaurantMapper::toDto)
                .toList();
        return ResponseEntity.ok(restaurants);
    }

}
