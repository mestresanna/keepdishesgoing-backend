package kdg.programming6.keepdishesgoing.restaurant.port.in;

import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;
import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;

import java.util.UUID;

public interface ChangeStateDishUseCase {
    void changeStateDish(UUID dish);
}
