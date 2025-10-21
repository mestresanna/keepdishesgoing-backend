package kdg.programming6.keepdishesgoing.restaurant.adapter.in.request;

import kdg.programming6.keepdishesgoing.common.vo.FoodTag;
import kdg.programming6.keepdishesgoing.restaurant.domain.*;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.RestaurantId;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateDishCommand;

import java.util.Set;
import java.util.UUID;

public record CreateDishRequest(
        String name,
        String type,
        Set<String> tags,
        String description,
        Float price,
        String pictureUrl,
        String state
) {
    public CreateDishCommand toCommand(UUID restaurantId) {
        return new CreateDishCommand(
                name,
                new RestaurantId(restaurantId),
                DishType.valueOf(type),
                tags != null ? tags.stream().map(FoodTag::new).collect(java.util.stream.Collectors.toSet()) : Set.of(),
                description,
                price,
                pictureUrl,
                DishState.valueOf(state),
                false,
                null
        );
    }
}
