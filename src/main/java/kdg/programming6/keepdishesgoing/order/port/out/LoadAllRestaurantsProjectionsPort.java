package kdg.programming6.keepdishesgoing.order.port.out;

import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjection;

import java.util.List;

public interface LoadAllRestaurantsProjectionsPort {
    List<RestaurantProjection> loadAllRestaurants();
}
