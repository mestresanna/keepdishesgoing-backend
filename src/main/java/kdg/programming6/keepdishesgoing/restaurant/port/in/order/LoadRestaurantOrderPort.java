package kdg.programming6.keepdishesgoing.restaurant.port.in.order;

import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrder;

import java.util.Optional;
import java.util.UUID;

public interface LoadRestaurantOrderPort {
    Optional<RestaurantOrder> findById(UUID restaurantOrderId);
}
