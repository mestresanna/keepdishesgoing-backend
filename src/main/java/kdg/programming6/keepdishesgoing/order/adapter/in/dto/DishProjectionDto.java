package kdg.programming6.keepdishesgoing.order.adapter.in.dto;

import java.math.BigDecimal;
import java.util.Set;

public record DishProjectionDto(
        String dishId,
        String name,
        String description,
        BigDecimal price,
        String pictureUrl,
        String dishType,
        Set<String> tags
) {}
