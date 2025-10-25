package kdg.programming6.keepdishesgoing.order.port.in.dish;

import kdg.programming6.keepdishesgoing.common.vo.FoodTag;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public record CreatedDishProjectionCommand (UUID dishId, UUID menuId, String name, UUID restaurantId, String type, Set<FoodTag> tags, String description, BigDecimal price, String pictureUrl, String status){
}
