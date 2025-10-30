package kdg.programming6.keepdishesgoing.order.adapter.in;

import jakarta.validation.Valid;
import kdg.programming6.keepdishesgoing.order.adapter.in.response.basket.BasketMapperDto;
import kdg.programming6.keepdishesgoing.order.adapter.in.response.order.OrderIdDto;
import kdg.programming6.keepdishesgoing.order.adapter.in.request.AddDishToBasketRequest;
import kdg.programming6.keepdishesgoing.order.adapter.in.request.CheckoutRequest;
import kdg.programming6.keepdishesgoing.order.adapter.in.request.RemoveDishToBasketRequest;
import kdg.programming6.keepdishesgoing.order.adapter.in.response.basket.BasketDto;
import kdg.programming6.keepdishesgoing.order.domain.Basket;
import kdg.programming6.keepdishesgoing.order.domain.Order;
import kdg.programming6.keepdishesgoing.order.domain.exceptions.DishUnavailableException;
import kdg.programming6.keepdishesgoing.order.port.in.basket.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders/basket")
public class BasketController {
    private final AddDishtoBasketUseCase addDishtoBasketUseCase;
    private final CheckoutBasketUseCase checkoutBasketUseCase;
    private final RemoveDishBasketUseCase removeDishBasketUseCase;
    private final FindBasketUseCase findBasketUseCase;
    private final BasketMapperDto basketMapperDto;

    public BasketController(
            AddDishtoBasketUseCase addDishtoBasketUseCase,
            CheckoutBasketUseCase checkoutBasketUseCase,
            RemoveDishBasketUseCase removeDishBasketUseCase,
            FindBasketUseCase findBasketUseCase,
            BasketMapperDto basketMapperDto) {
        this.addDishtoBasketUseCase = addDishtoBasketUseCase;
        this.checkoutBasketUseCase = checkoutBasketUseCase;
        this.removeDishBasketUseCase = removeDishBasketUseCase;
        this.findBasketUseCase = findBasketUseCase;
        this.basketMapperDto = basketMapperDto;
    }

    @GetMapping("/{basketId}")
    public ResponseEntity<BasketDto> getBasket(@PathVariable UUID basketId) {
        Basket basket = findBasketUseCase.findById(basketId);
        BasketDto basketDto = basketMapperDto.toDto(basket);
        return ResponseEntity.ok(basketDto);
    }

    @PatchMapping({"/{basketId}/items", "/items"})
    public ResponseEntity<BasketDto> addDishToBasket(
            @PathVariable(required = false) UUID basketId,
            @Valid @RequestBody AddDishToBasketRequest request) {
        AddDishToBasketCommand command = new AddDishToBasketCommand(
                basketId,
                request.menuId(),
                request.dishId(),
                request.restaurantId(),
                request.quantity());

        Basket basket = addDishtoBasketUseCase.addDishtoBasket(command);

        BasketDto basketDto = basketMapperDto.toDto(basket);
        return ResponseEntity.ok(basketDto);
    }

    @PatchMapping("/{basketId}/removeItem")
    public ResponseEntity<BasketDto> removeDishToBasket(
            @PathVariable UUID basketId,
            @Valid @RequestBody RemoveDishToBasketRequest request) {
        RemoveDishBasketCommand command = new RemoveDishBasketCommand(
                basketId,
                request.menuId(),
                request.dishId());

        removeDishBasketUseCase.removeDishtoBasket(command);

        Basket basket = findBasketUseCase.findById(basketId);
        BasketDto basketDto = basketMapperDto.toDto(basket);
        return ResponseEntity.ok(basketDto);
    }

    @PostMapping("/{basketId}/checkout")
    public ResponseEntity<?> checkout(
            @PathVariable UUID basketId,
            @Valid @RequestBody CheckoutRequest request) {
        CheckoutBasketCommand command = new CheckoutBasketCommand(
                basketId,
                request.firstName(),
                request.lastName(),
                request.email(),
                request.street(),
                request.number(),
                request.city(),
                request.country(),
                request.postalCode(),
                request.message()
        );
        try {
            Order order = checkoutBasketUseCase.checkout(command);
            return ResponseEntity.ok(new OrderIdDto(order.getOrderId()));
        } catch (DishUnavailableException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", e.getMessage(),
                    "unavailableIds", e.getUnavailableDishIds()
            ));
        }
    }
}