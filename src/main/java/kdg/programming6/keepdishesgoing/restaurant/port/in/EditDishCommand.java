package kdg.programming6.keepdishesgoing.restaurant.port.in;

import kdg.programming6.keepdishesgoing.restaurant.domain.DishType;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.FoodTag;

import java.util.Set;
import java.util.UUID;

public record EditDishCommand(UUID dishId, String name, String description, Float price, String pictureUrl, DishType type, Set<FoodTag> tags) {
}
