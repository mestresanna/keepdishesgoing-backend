package kdg.programming6.keepdishesgoing.restaurant.port.in;

import kdg.programming6.keepdishesgoing.common.vo.FoodTag;
import kdg.programming6.keepdishesgoing.restaurant.domain.DishType;

import java.util.Set;
import java.util.UUID;

public record EditDishCommand(UUID dishId, String name, String description, Float price, String pictureUrl, DishType type, Set<FoodTag> tags) {
}
