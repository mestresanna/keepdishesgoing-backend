package kdg.programming6.keepdishesgoing.restaurant.port.in.order;

import kdg.programming6.keepdishesgoing.restaurant.domain.RestaurantOrder;

public interface RestaurantOrderCreatedProjector {
    RestaurantOrder saveRestaurantOrder(RestaurantOrderCommand restaurantOrder);
}
