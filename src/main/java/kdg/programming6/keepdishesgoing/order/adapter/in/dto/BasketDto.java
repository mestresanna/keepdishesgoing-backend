package kdg.programming6.keepdishesgoing.order.adapter.in.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record BasketDto(
        UUID basketId,
        UUID restaurantId,
        List<BasketItemDto> items,
        BigDecimal totalPrice,
        int itemCount
) {
}
