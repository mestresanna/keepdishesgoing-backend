package kdg.programming6.keepdishesgoing.restaurant.port.in;

import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;

import java.util.List;
import java.util.UUID;

public interface FindAllDishesUseCase {
    List<Dish> findAllDishesByRestaurant(UUID restaurantId);
}
