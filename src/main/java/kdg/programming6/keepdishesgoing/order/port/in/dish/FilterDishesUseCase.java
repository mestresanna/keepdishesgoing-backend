package kdg.programming6.keepdishesgoing.order.port.in.dish;

import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface FilterDishesUseCase {
    List<DishProjection> filterDishes(UUID menuId, String type, Set<String> tags);
}
