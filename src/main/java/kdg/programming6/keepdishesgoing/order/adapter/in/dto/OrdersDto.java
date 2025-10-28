package kdg.programming6.keepdishesgoing.order.adapter.in.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrdersDto(
        UUID orderId,
        UUID restaurantId,
        CustomerOrderDto customer,
        List<OrderItemDto> items,
        BigDecimal totalPrice,
        String status,
        String message,
        String rejectionReason
) {
}
