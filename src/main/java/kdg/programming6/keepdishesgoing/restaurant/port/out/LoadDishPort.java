package kdg.programming6.keepdishesgoing.restaurant.port.out;

import kdg.programming6.keepdishesgoing.restaurant.domain.Dish;

import java.util.Optional;
import java.util.UUID;

public interface LoadDishPort {
    Optional<Dish> loadById(UUID id);

}
