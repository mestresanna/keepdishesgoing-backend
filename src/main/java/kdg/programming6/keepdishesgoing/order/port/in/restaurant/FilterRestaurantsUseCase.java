package kdg.programming6.keepdishesgoing.order.port.in.restaurant;

import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjection;

import java.util.List;

public interface FilterRestaurantsUseCase {
    List<RestaurantProjection> filterRestaurants(String cuisine, String priceRange);
}
