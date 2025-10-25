package kdg.programming6.keepdishesgoing.order.adapter.in.request;

import java.math.BigDecimal;
import java.util.UUID;

public record AddDishToBasketRequest(
        UUID menuId,
        UUID dishId,
        UUID restaurantId,
        Integer quantity
) {}
