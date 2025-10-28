package kdg.programming6.keepdishesgoing.order.port.out.restaurant;

import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjection;

public interface SaveRestaurantProjectionPort {

    RestaurantProjection save(RestaurantProjection restaurantProjection);
}
