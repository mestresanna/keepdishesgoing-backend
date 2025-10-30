package kdg.programming6.keepdishesgoing.order.adapter.in.response.order;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemDto(
        UUID dishId,
        String dishName,
        int quantity,
        BigDecimal pricePerItem,
        BigDecimal totalPrice
) {
}
