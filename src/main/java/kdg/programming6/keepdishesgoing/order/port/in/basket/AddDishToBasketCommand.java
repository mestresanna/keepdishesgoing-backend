package kdg.programming6.keepdishesgoing.order.port.in.basket;

import java.math.BigDecimal;
import java.util.UUID;

public record AddDishToBasketCommand(UUID basketId, UUID menuId, UUID dishId, UUID restaurantId, Integer quantity) {
}