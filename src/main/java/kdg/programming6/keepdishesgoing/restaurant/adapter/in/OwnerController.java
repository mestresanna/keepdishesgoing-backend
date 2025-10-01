package kdg.programming6.keepdishesgoing.restaurant.adapter.in;

import kdg.programming6.keepdishesgoing.restaurant.adapter.in.request.CreateOwnerRequest;
import kdg.programming6.keepdishesgoing.restaurant.adapter.in.request.CreateRestaurantRequest;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateOwnerUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {
    private final CreateOwnerUseCase createOwnerUseCase;

    public OwnerController(CreateOwnerUseCase createOwnerUseCase) {
        this.createOwnerUseCase = createOwnerUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> createOwner(@RequestBody CreateOwnerRequest request) {
        createOwnerUseCase.handle(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
