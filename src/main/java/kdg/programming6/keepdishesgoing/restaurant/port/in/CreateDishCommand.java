package kdg.programming6.keepdishesgoing.restaurant.port.in;

import kdg.programming6.keepdishesgoing.restaurant.domain.*;

import java.util.Set;

public record CreateDishCommand(
        DishId dishId,
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
