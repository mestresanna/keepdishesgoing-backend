package kdg.programming6.keepdishesgoing.restaurant.port.out;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadRestaurantPort {
    Optional<Restaurant> loadById(UUID id);
}
