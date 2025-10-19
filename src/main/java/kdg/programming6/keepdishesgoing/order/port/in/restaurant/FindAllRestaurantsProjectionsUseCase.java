package kdg.programming6.keepdishesgoing.order.port.in.restaurant;
import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjection;

import java.util.List;

public interface FindAllRestaurantsProjectionsUseCase {
    List<RestaurantProjection> findAllRestaurants();
}
