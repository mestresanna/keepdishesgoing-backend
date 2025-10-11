package kdg.programming6.keepdishesgoing.restaurant.port.out;

import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;

public interface DishPort {
    void save(Dish dish);
}
