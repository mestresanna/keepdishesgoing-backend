package kdg.programming6.keepdishesgoing.restaurant.port.out.restaurant;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;

public interface SaveRestaurantPort {
    void save(Restaurant restaurant);
}
