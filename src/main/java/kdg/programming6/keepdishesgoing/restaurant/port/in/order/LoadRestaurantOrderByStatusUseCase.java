package kdg.programming6.keepdishesgoing.restaurant.port.in.order;

import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrder;
import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrderStatus;

import java.util.List;
import java.util.UUID;

public interface LoadRestaurantOrderByStatusUseCase {
    List<RestaurantOrder> findAllRestaurantOrderByStatusAndRestaurantId(List<RestaurantOrderStatus> restaurantOrders, UUID restaurantId);
    List<RestaurantOrder> findAllRestaurantOrderByStatus(RestaurantOrderStatus restaurantOrders);


}
