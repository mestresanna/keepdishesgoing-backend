package kdg.programming6.keepdishesgoing.restaurant.adapter.in;

import kdg.programming6.keepdishesgoing.restaurant.adapter.in.request.CreateRestaurantRequest;
import kdg.programming6.keepdishesgoing.restaurant.adapter.in.response.restaurant.RestaurantDto;
import kdg.programming6.keepdishesgoing.restaurant.adapter.in.response.restaurant.RestaurantMapperDto;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.OpeningHours;
import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;
import kdg.programming6.keepdishesgoing.restaurant.port.in.restaurant.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    private final CreateRestaurantUseCase createRestaurant;
    private final FindRestaurantUseCase findRestaurant;
    private final FindAllRestaurantsUseCase findAllRestaurants;
    private final SetOpeningHoursUseCase setOpeningHours;
    private final RestaurantMapperDto restaurantMapperDto;

    public RestaurantController(CreateRestaurantUseCase createRestaurant, FindRestaurantUseCase findRestaurant, FindAllRestaurantsUseCase findAllRestaurants, SetOpeningHoursUseCase setOpeningHours, RestaurantMapperDto restaurantMapperDto) {
        this.createRestaurant = createRestaurant;
        this.findRestaurant = findRestaurant;
        this.findAllRestaurants = findAllRestaurants;
        this.setOpeningHours = setOpeningHours;
        this.restaurantMapperDto = restaurantMapperDto;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<Void> createRestaurant(@RequestBody CreateRestaurantRequest request, @AuthenticationPrincipal Jwt token) {
        String subject = token.getClaimAsString("sub");
        UUID ownerId = UUID.fromString(subject);
        logger.info("createRestaurant request ownerId: " + ownerId);
        createRestaurant.createRestaurant(request.toCommand(ownerId));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable UUID id) {
        return ResponseEntity.ok(findRestaurant.findRestaurant(id));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurant(@AuthenticationPrincipal Jwt token) {
        String subject = token.getClaimAsString("sub");
        UUID ownerId = UUID.fromString(subject);
        var restaurants = findAllRestaurants.findAllByOwner(ownerId).stream()
                .map(restaurantMapperDto::toDto)
                .toList();;
        return ResponseEntity.ok(restaurants);
    }

    @PatchMapping("/{restaurantId}/opening-hours")
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<Void> setOpeningHours(
            @PathVariable UUID restaurantId,
            @RequestBody OpeningHours newOpeningHours,
            @AuthenticationPrincipal Jwt token
    ) {
        String subject = token.getClaimAsString("sub");
        UUID ownerId = UUID.fromString(subject);
        logger.info("createRestaurant request ownerId: " + ownerId);
        SetOpeningHoursCommand command = new SetOpeningHoursCommand(restaurantId, ownerId, newOpeningHours);
        setOpeningHours.setOpeningHours(command);
        return ResponseEntity.noContent().build();
    }

}
