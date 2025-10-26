package kdg.programming6.keepdishesgoing.order.adapter.in;

import jakarta.validation.Valid;
import kdg.programming6.keepdishesgoing.order.adapter.in.request.AddDishToBasketRequest;
import kdg.programming6.keepdishesgoing.order.adapter.in.request.CheckoutRequest;
import kdg.programming6.keepdishesgoing.order.domain.exceptions.BasketNotFoundException;
import kdg.programming6.keepdishesgoing.order.domain.exceptions.DishUnavailableException;
import kdg.programming6.keepdishesgoing.order.port.in.basket.AddDishToBasketCommand;
import kdg.programming6.keepdishesgoing.order.port.in.basket.AddDishtoBasketUseCase;
import kdg.programming6.keepdishesgoing.order.port.in.basket.CheckoutBasketCommand;
import kdg.programming6.keepdishesgoing.order.port.in.basket.CheckoutBasketUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders/basket")
public class BasketController {
    private final AddDishtoBasketUseCase addDishtoBasketUseCase;
    private final CheckoutBasketUseCase checkoutBasketUseCase;

    public BasketController(AddDishtoBasketUseCase addDishtoBasketUseCase, CheckoutBasketUseCase checkoutBasketUseCase) {
        this.addDishtoBasketUseCase = addDishtoBasketUseCase;
        this.checkoutBasketUseCase = checkoutBasketUseCase;
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

    @PostMapping("/{basketId}/checkout")
    public ResponseEntity<?> checkout(
            @PathVariable UUID basketId,
            @Valid @RequestBody CheckoutRequest request) {

        try {
            CheckoutBasketCommand command = new CheckoutBasketCommand(
                    basketId,
                    request.firstName(),
                    request.lastName(),
                    request.email(),
                    request.street(),
                    request.number(),
                    request.postalCode(),
                    request.city(),
                    request.country(),
                    request.message()
            );

            checkoutBasketUseCase.checkout(command);

            return ResponseEntity.ok(Map.of(
                    "message", "Order placed successfully",
                    "basketId", basketId
            ));

        } catch (BasketNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));

        } catch (DishUnavailableException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of(
                            "error", "Some dishes are no longer available",
                            "unavailableDishes", e.getUnavailableDishIds()
                    ));

        } catch (IllegalStateException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
