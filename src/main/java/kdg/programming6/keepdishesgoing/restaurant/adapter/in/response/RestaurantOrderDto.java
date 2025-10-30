package kdg.programming6.keepdishesgoing.restaurant.adapter.in.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record RestaurantOrderDto(
        UUID orderId,
        UUID restaurantId,
        String customerName,
        String status,
        String rejectionReason,
        Instant createdAt,
        BigDecimal totalPrice,
        List<RestaurantOrderItemDto> items
) {}
