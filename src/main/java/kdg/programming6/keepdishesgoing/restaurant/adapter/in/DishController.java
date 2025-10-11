package kdg.programming6.keepdishesgoing.restaurant.adapter.in;

import kdg.programming6.keepdishesgoing.restaurant.adapter.in.request.CreateDishRequest;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateDishCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateDishUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.in.PublishDishUseCase;
import kdg.programming6.keepdishesgoing.restaurant.port.in.UnpublishDishUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    private final CreateDishUseCase createDishUseCase;
    private final PublishDishUseCase publishDishUseCase;
    private final UnpublishDishUseCase unpublishDishUseCase;

    public DishController(CreateDishUseCase createDishUseCase, PublishDishUseCase publishDishUseCase, UnpublishDishUseCase unpublishDishUseCase) {
        this.createDishUseCase = createDishUseCase;
        this.publishDishUseCase = publishDishUseCase;
        this.unpublishDishUseCase = unpublishDishUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> createDish(@RequestBody CreateDishRequest request) {
        createDishUseCase.handle(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{dishId}/publish")
    public ResponseEntity<Void> publishDish(@PathVariable UUID dishId) {
        publishDishUseCase.handle(dishId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{dishId}/unpublish")
    public ResponseEntity<Void> unpublishDish(@PathVariable UUID dishId) {
        unpublishDishUseCase.handle(dishId);
        return ResponseEntity.noContent().build();
    }
}
