package kdg.programming6.keepdishesgoing.restaurant.port.out.order;

import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrder;
import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrderStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadRestaurantOrderPort {
    Optional<RestaurantOrder> findById(UUID restaurantOrderId);
    List<RestaurantOrder> findAllByStatusWithRestaurantId(List<RestaurantOrderStatus> restaurantOrderStatuses, UUID restaurantId);
    List<RestaurantOrder> findAllByStatus(RestaurantOrderStatus restaurantOrderStatuses);


}
