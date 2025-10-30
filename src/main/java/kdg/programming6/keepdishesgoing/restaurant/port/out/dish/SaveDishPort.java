package kdg.programming6.keepdishesgoing.restaurant.port.out.dish;

import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;

public interface SaveDishPort {
    void save(Dish dish);
}
