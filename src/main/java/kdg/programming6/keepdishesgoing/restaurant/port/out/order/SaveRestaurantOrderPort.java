package kdg.programming6.keepdishesgoing.restaurant.port.out.order;

import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrder;

public interface SaveRestaurantOrderPort {
    void save(RestaurantOrder restaurantOrder);
}
