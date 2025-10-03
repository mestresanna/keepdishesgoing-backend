package kdg.programming6.keepdishesgoing.restaurant.port.out;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;

import java.util.Optional;
import java.util.UUID;

public interface RestaurantPort {
    void save(Restaurant restaurant);
    Optional<Restaurant> findById(UUID id);
}
