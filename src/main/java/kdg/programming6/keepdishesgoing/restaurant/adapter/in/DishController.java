package kdg.programming6.keepdishesgoing.restaurant.adapter.in;

import kdg.programming6.keepdishesgoing.restaurant.adapter.in.request.CreateDishRequest;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateDishCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateDishUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    private final CreateDishUseCase createDishUseCase;

    public DishController(CreateDishUseCase createDishUseCase) {
        this.createDishUseCase = createDishUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> createDish(@RequestBody CreateDishRequest request) {
        createDishUseCase.handle(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
