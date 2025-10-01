package kdg.programming6.keepdishesgoing.restaurant.adapter.in;

import kdg.programming6.keepdishesgoing.restaurant.adapter.in.request.CreateRestaurantRequest;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateRestaurantCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateRestaurantUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final CreateRestaurantUseCase createRestaurant;

    public RestaurantController(CreateRestaurantUseCase createRestaurant) {
        this.createRestaurant = createRestaurant;
    }

    @PostMapping
    public ResponseEntity<Void> createRestaurant(@RequestBody CreateRestaurantRequest request) {
        createRestaurant.handle(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
