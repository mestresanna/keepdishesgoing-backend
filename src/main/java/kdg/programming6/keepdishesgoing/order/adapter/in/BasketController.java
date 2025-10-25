package kdg.programming6.keepdishesgoing.order.adapter.in;

import kdg.programming6.keepdishesgoing.order.adapter.in.request.AddDishToBasketRequest;
import kdg.programming6.keepdishesgoing.order.port.in.basket.AddDishToBasketCommand;
import kdg.programming6.keepdishesgoing.order.port.in.basket.AddDishtoBasketUseCase;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.OpeningHours;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders/basket")
public class BasketController {
    private final AddDishtoBasketUseCase addDishtoBasketUseCase;

    public BasketController(AddDishtoBasketUseCase addDishtoBasketUseCase) {
        this.addDishtoBasketUseCase = addDishtoBasketUseCase;
    }


    @PatchMapping({"/{basketId}/items", "/items"})
    public ResponseEntity<Void> addDishToBasket(
            @PathVariable(required = false) UUID basketId,
            @RequestBody AddDishToBasketRequest request
    ) {
        AddDishToBasketCommand command = new AddDishToBasketCommand(
                basketId,
                request.menuId(),
                request.dishId(),
                request.restaurantId(),
                request.quantity());

        addDishtoBasketUseCase.addDishtoBasket(command);
        return ResponseEntity.noContent().build();
    }
}
