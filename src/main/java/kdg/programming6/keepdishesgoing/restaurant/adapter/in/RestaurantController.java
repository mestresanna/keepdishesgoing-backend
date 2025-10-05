package kdg.programming6.keepdishesgoing.restaurant.adapter.in;

import kdg.programming6.keepdishesgoing.restaurant.adapter.in.request.CreateRestaurantRequest;
import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateRestaurantCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateRestaurantUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.in.FindAllRestaurantsUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.in.FindRestaurantUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final CreateRestaurantUseCase createRestaurant;
    private final FindRestaurantUseCase findRestaurant;
    private final FindAllRestaurantsUseCase findAllRestaurants;

    public RestaurantController(CreateRestaurantUseCase createRestaurant, FindRestaurantUseCase findRestaurant, FindAllRestaurantsUseCase findAllRestaurants) {
        this.createRestaurant = createRestaurant;
        this.findRestaurant = findRestaurant;
        this.findAllRestaurants = findAllRestaurants;
    }

    @PostMapping
    public ResponseEntity<Void> createRestaurant(@RequestBody CreateRestaurantRequest request) {
        createRestaurant.handle(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable UUID id) {
        return ResponseEntity.ok(findRestaurant.findRestaurant(id));
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurant() {
        return ResponseEntity.ok(findAllRestaurants.findAll());
    }
}
