package kdg.programming6.keepdishesgoing.restaurant.port.in;

import kdg.programming6.keepdishesgoing.restaurant.domain.*;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.DishId;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.FoodTag;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.RestaurantId;

import java.util.Set;

public record CreateDishCommand(
        String name,
        RestaurantId restaurantId,
        DishType type,
        Set<FoodTag>tags,
        String description,
        Float price,
        String pictureUrl,
        DishState state,
        boolean hasPendingChanges,
        PublishDishesSchedule scheduledChange
) {
}
