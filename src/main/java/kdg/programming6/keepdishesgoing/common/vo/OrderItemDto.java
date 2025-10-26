package kdg.programming6.keepdishesgoing.common.vo;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemDto(
        UUID dishId,
        int quantity,
        BigDecimal priceAtOrder
) {}