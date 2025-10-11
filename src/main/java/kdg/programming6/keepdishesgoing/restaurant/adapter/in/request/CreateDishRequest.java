package kdg.programming6.keepdishesgoing.restaurant.adapter.in.request;

import kdg.programming6.keepdishesgoing.restaurant.domain.*;
import kdg.programming6.keepdishesgoing.restaurant.port.in.CreateDishCommand;

import java.util.Set;
import java.util.UUID;

public record CreateDishRequest(
        UUID dishId,
        String name,
        UUID restaurantId,
        String type,
        Set<String> tags,
        String description,
        Float price,
        String pictureUrl,
        String state
) {
    public CreateDishCommand toCommand() {
        return new CreateDishCommand(
                new DishId(dishId),
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
