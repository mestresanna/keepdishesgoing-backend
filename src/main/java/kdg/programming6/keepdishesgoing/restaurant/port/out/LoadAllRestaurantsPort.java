package kdg.programming6.keepdishesgoing.restaurant.port.out;

import kdg.programming6.keepdishesgoing.restaurant.domain.Restaurant;

import java.util.List;

public interface LoadAllRestaurantsPort {
    List<Restaurant> loadAll();

}
