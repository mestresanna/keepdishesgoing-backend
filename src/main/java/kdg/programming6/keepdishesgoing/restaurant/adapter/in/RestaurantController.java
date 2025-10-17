package kdg.programming6.keepdishesgoing.restaurant.adapter.in;

import kdg.programming6.keepdishesgoing.restaurant.adapter.in.request.CreateRestaurantRequest;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.OpeningHours;
import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.port.in.*;
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
    private final SetOpeningHoursUseCase setOpeningHours;

    public RestaurantController(CreateRestaurantUseCase createRestaurant, FindRestaurantUseCase findRestaurant, FindAllRestaurantsUseCase findAllRestaurants, SetOpeningHoursUseCase setOpeningHours) {
        this.createRestaurant = createRestaurant;
        this.findRestaurant = findRestaurant;
        this.findAllRestaurants = findAllRestaurants;
        this.setOpeningHours = setOpeningHours;
    }

    @PostMapping
    public ResponseEntity<Void> createRestaurant(@RequestBody CreateRestaurantRequest request) {
        createRestaurant.createRestaurant(request.toCommand());
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

    @PatchMapping("/{restaurantId}/opening-hours")
    public ResponseEntity<Void> setOpeningHours(
            @PathVariable UUID restaurantId,
            @RequestBody OpeningHours newOpeningHours
            ,@RequestHeader("X-Owner-Id") UUID ownerId
    ) {
        SetOpeningHoursCommand command = new SetOpeningHoursCommand(restaurantId, ownerId, newOpeningHours);
        setOpeningHours.setOpeningHours(command);
        return ResponseEntity.noContent().build();
    }

}
