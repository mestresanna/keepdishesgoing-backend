package kdg.programming6.keepdishesgoing.order.domain.projections;

import kdg.programming6.keepdishesgoing.order.domain.exceptions.RestaurantProjectionNotFoundException;
import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.OwnerNotFoundException;

import java.util.UUID;

public record RestaurantProjectionId (UUID restaurantId) {
    public static RestaurantProjectionId of(UUID restaurantId) {
        return new RestaurantProjectionId(restaurantId);
    }
    public static void notRestaurantProjectionFound(UUID restaurantId) throws RestaurantProjectionNotFoundException {
        throw new RestaurantProjectionNotFoundException(String.format("Owner with UUID %s was not found", restaurantId.toString()));
    }

}
