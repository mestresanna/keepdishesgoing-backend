package kdg.programming6.keepdishesgoing.order.adapter.in.dto;

import java.util.Set;

public record DishProjectionDto(
        String dishId,
        String name,
        String description,
        Float price,
        String pictureUrl,
        String type,
        Set<String> tags
) {}
