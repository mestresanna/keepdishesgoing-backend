package kdg.programming6.keepdishesgoing.restaurant.adapter.in;

import kdg.programming6.keepdishesgoing.restaurant.adapter.in.request.CreateDishRequest;
import kdg.programming6.keepdishesgoing.restaurant.adapter.in.request.EditDishRequest;
import kdg.programming6.keepdishesgoing.restaurant.port.in.*;
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
    private final EditDishUseCase editDishUseCase;

    public DishController(CreateDishUseCase createDishUseCase, PublishDishUseCase publishDishUseCase, UnpublishDishUseCase unpublishDishUseCase, EditDishUseCase editDishUseCase) {
        this.createDishUseCase = createDishUseCase;
        this.publishDishUseCase = publishDishUseCase;
        this.unpublishDishUseCase = unpublishDishUseCase;
        this.editDishUseCase = editDishUseCase;
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

    @PatchMapping("/{dishId}")
    public ResponseEntity<Void> editDish(@PathVariable UUID dishId, @RequestBody EditDishRequest request) {
        editDishUseCase.handle(request.toCommand(dishId));
        return ResponseEntity.ok().build();
    }


}
