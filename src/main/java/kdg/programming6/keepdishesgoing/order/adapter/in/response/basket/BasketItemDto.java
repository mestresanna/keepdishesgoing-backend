package kdg.programming6.keepdishesgoing.order.adapter.in.response.basket;

import java.math.BigDecimal;
import java.util.UUID;

public record BasketItemDto(
        UUID dishId,
        UUID menuId,
        String dishName,
        int quantity,
        BigDecimal pricePerItem,
        BigDecimal totalPrice
) {
}