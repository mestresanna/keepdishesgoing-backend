package kdg.programming6.keepdishesgoing.restaurant.port.out.restaurant;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;

import java.util.List;
import java.util.UUID;

public interface LoadAllRestaurantsPort {
    List<Restaurant> loadAllByOwner(UUID ownerId);

}
