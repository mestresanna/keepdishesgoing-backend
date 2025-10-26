package kdg.programming6.keepdishesgoing.restaurant.port.in.restaurant;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;

import java.util.UUID;

public interface FindRestaurantUseCase {
    Restaurant findRestaurant(UUID id);

}
