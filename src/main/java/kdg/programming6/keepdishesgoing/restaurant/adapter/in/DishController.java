package kdg.programming6.keepdishesgoing.restaurant.adapter.in;

import kdg.programming6.keepdishesgoing.restaurant.adapter.in.request.CreateDishRequest;
import kdg.programming6.keepdishesgoing.restaurant.adapter.in.request.EditDishRequest;
import kdg.programming6.keepdishesgoing.restaurant.adapter.in.response.DishDto;
import kdg.programming6.keepdishesgoing.restaurant.adapter.in.response.DishMapperDto;
import kdg.programming6.keepdishesgoing.restaurant.adapter.out.dish.DishMapper;
import kdg.programming6.keepdishesgoing.restaurant.port.in.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    private final CreateDishUseCase createDishUseCase;
    private final PublishDishUseCase publishDishUseCase;
    private final UnpublishDishUseCase unpublishDishUseCase;
    private final EditDishUseCase editDishUseCase;
    private final ChangeStateDishUseCase changeStateDishUseCase;
    private final FindAllDishesUseCase findAllDishesUseCase;
    private final FindAllRestaurantsUseCase findAllRestaurants;
    private final DishMapper dishMapper;

    public DishController(CreateDishUseCase createDishUseCase, PublishDishUseCase publishDishUseCase, UnpublishDishUseCase unpublishDishUseCase, EditDishUseCase editDishUseCase, ChangeStateDishUseCase changeStateDishUseCase, FindAllDishesUseCase findAllDishesUseCase, FindAllRestaurantsUseCase findAllRestaurants, DishMapper dishMapper) {
        this.createDishUseCase = createDishUseCase;
        this.publishDishUseCase = publishDishUseCase;
        this.unpublishDishUseCase = unpublishDishUseCase;
        this.editDishUseCase = editDishUseCase;
        this.changeStateDishUseCase = changeStateDishUseCase;
        this.findAllDishesUseCase = findAllDishesUseCase;
        this.findAllRestaurants = findAllRestaurants;
        this.dishMapper = dishMapper;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<Void> createDish(@RequestBody CreateDishRequest request,
                                           @AuthenticationPrincipal Jwt token
    ) {
        String subject = token.getClaimAsString("sub");
        UUID ownerId = UUID.fromString(subject);
        var restaurants = findAllRestaurants.findAllByOwner(ownerId);
        for  (var restaurant : restaurants) {
            createDishUseCase.createDish(request.toCommand(restaurant.getRestaurantId()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{dishId}/publish")
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<Void> publishDish(@PathVariable UUID dishId) {
        publishDishUseCase.publishDish(dishId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{dishId}/unpublish")
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<Void> unpublishDish(@PathVariable UUID dishId) {
        unpublishDishUseCase.unplublishDish(dishId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{dishId}")
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<Void> editDish(@PathVariable UUID dishId, @RequestBody EditDishRequest request) {
        editDishUseCase.editDish(request.toCommand(dishId));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{dishId}/changeState")
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<Void> changeDish(@PathVariable UUID dishId) {
        changeStateDishUseCase.changeStateDish(dishId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<List<DishDto>> getAllDishes(
            @AuthenticationPrincipal Jwt token
    ) {
        String subject = token.getClaimAsString("sub");
        UUID ownerId = UUID.fromString(subject);
        var restaurants = findAllRestaurants.findAllByOwner(ownerId);
        List<DishDto> allDishes = restaurants.stream()
                .flatMap(restaurant -> findAllDishesUseCase.findAllDishesByRestaurant(restaurant.getRestaurantId())
                        .stream()
                        .map(DishMapperDto::toDto))
                .toList();

        return ResponseEntity.ok(allDishes);
    }
}
