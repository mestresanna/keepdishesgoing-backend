package kdg.programming6.keepdishesgoing.order.port.in.basket;

import java.util.UUID;

public record RemoveDishBasketCommand(UUID basketId, UUID menuId, UUID dishId) {
}
