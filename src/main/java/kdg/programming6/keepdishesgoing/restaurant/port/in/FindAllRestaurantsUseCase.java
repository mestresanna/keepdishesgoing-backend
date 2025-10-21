package kdg.programming6.keepdishesgoing.restaurant.port.in;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;

import java.util.List;
import java.util.UUID;

public interface FindAllRestaurantsUseCase {
    List<Restaurant> findAllByOwner(UUID ownerId);
}
