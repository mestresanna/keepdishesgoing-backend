package kdg.programming6.keepdishesgoing.restaurant.adapter.in.response;


import java.math.BigDecimal;
import java.util.UUID;

public record RestaurantOrderItemDto(
        UUID dishId,
        String dishName,
        int quantity,
        BigDecimal priceAtOrder
) {}