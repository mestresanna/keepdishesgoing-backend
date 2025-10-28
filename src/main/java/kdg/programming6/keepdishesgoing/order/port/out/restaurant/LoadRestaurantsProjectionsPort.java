package kdg.programming6.keepdishesgoing.order.port.out.restaurant;

import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadRestaurantsProjectionsPort {
    List<RestaurantProjection> loadAllRestaurants();
    Optional<RestaurantProjection> loadbyId(UUID restaurantId);

}
