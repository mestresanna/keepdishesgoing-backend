package kdg.programming6.keepdishesgoing.restaurant.port.out.menu;

import kdg.programming6.keepdishesgoing.restaurant.domain.Menu;
import kdg.programming6.keepdishesgoing.restaurant.domain.ov.RestaurantId;

import java.util.Optional;

public interface LoadMenuPort {
    Optional<Menu> loadMenuByRestaurantId(RestaurantId restaurantId);

}
