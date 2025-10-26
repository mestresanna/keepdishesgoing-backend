package kdg.programming6.keepdishesgoing.restaurant.port.in.order;

import java.math.BigDecimal;
import java.util.UUID;

public record RestaurantOrderItemCommand (UUID dishId, Integer quantity, BigDecimal priceAtOrder) {
}

