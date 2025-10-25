package kdg.programming6.keepdishesgoing.restaurant.adapter.in.request;

import kdg.programming6.keepdishesgoing.common.vo.FoodTag;
import kdg.programming6.keepdishesgoing.restaurant.domain.DishType;
import kdg.programming6.keepdishesgoing.restaurant.port.in.EditDishCommand;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public record EditDishRequest(UUID dishId, String name, String description, BigDecimal price, String pictureUrl, DishType type, Set<FoodTag> tags) {
    public EditDishCommand toCommand(UUID dishId) {
        return new EditDishCommand(dishId, name, description, price, pictureUrl, type, tags);
    }
}
