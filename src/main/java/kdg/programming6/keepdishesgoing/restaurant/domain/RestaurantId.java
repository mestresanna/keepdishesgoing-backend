package kdg.programming6.keepdishesgoing.restaurant.domain;

import kdg.programming6.keepdishesgoing.restaurant.domain.exceptions.RestaurantNotFoundException;

import java.util.UUID;

public record RestaurantId (UUID restaurantId){
    public static RestaurantId of(UUID restaurantId) {
        return new RestaurantId(restaurantId);
    }

    public static void notRestaurantFound(UUID restaurantId) throws RestaurantNotFoundException {
        throw new RestaurantNotFoundException(String.format("Owner with UUID %s was not found", restaurantId));
    }
}
