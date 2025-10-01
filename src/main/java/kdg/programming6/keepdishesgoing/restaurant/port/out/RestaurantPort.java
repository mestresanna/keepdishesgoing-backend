package kdg.programming6.keepdishesgoing.restaurant.port.out;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;

public interface RestaurantPort {
    void save(Restaurant restaurant);
}
