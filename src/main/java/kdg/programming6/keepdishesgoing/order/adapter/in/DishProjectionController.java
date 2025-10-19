package kdg.programming6.keepdishesgoing.order.adapter.in;

import kdg.programming6.keepdishesgoing.order.adapter.in.dto.*;
import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;
import kdg.programming6.keepdishesgoing.order.port.in.dish.FilterDishesUseCase;
import kdg.programming6.keepdishesgoing.order.port.in.restaurant.FindRestaurantDetailsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders/dishes")
public class DishProjectionController {
    private final FilterDishesUseCase filterDishes;
    private final FindRestaurantDetailsUseCase findRestaurantDetails;
    private final MenuProjectionMapperDto menuMapper;


    public DishProjectionController( FilterDishesUseCase filterDishes, FindRestaurantDetailsUseCase findRestaurantDetails,  MenuProjectionMapperDto menuMapper) {
        this.filterDishes = filterDishes;
        this.findRestaurantDetails = findRestaurantDetails;
        this.menuMapper = menuMapper;
    }


    @GetMapping("/{restaurantId}/filter")
    public ResponseEntity<List<MenuProjectionDto>> sortingDishes(
            @PathVariable UUID restaurantId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Set<String> tags,
            @RequestParam(required = false, defaultValue = "price") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String direction
    ) {
        var menus = findRestaurantDetails.findAllMenusForRestaurant(restaurantId);
        var menuDtos = menus.stream().map(menu -> {
            var filteredDishes = filterDishes.filterDishes(menu.getId(), type, tags, sortBy, direction);
            return menuMapper.toDtoWithDishes(menu, filteredDishes);
        }).toList();

        return ResponseEntity.ok(menuDtos);
    }
}
