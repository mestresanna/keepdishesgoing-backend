package kdg.programming6.keepdishesgoing.restaurant.port.out;

import kdg.programming6.keepdishesgoing.restaurant.domain.Menu;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.RestaurantId;

import java.util.Optional;
import java.util.UUID;

public interface LoadMenuPort {
    Optional<Menu> loadById(UUID id);
    Optional<Menu> loadMenuByRestaurantId(RestaurantId restaurantId);

}
