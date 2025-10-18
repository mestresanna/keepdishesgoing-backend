package kdg.programming6.keepdishesgoing.common.vo;

import java.util.Set;
import java.util.UUID;
import kdg.programming6.keepdishesgoing.common.vo.FoodTag;

public record PublishedDishDto(
    UUID dishId,
    String name,
    UUID restaurantId,
    String type,
    Set<FoodTag> tags,
    String description,
    Float price,
    String pictureUrl,
    String status
) {}
