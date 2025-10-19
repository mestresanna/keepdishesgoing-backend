package kdg.programming6.keepdishesgoing.order.port.in.restaurant;

import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;
import kdg.programming6.keepdishesgoing.order.domain.projections.MenuProjection;
import kdg.programming6.keepdishesgoing.order.domain.projections.RestaurantProjection;

import java.util.List;
import java.util.UUID;

public interface FindRestaurantDetailsUseCase {
    RestaurantProjection findRestaurantDetails(UUID restaurantId);
    List<MenuProjection> findAllMenusForRestaurant(UUID restaurantId);
}
