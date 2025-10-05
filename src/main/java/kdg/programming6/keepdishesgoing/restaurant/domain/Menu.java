package kdg.programming6.keepdishesgoing.restaurant.domain;

import java.util.Map;
import java.util.UUID;

public class Menu {
    private UUID id;
    private RestaurantId restaurantId;
    private Map<Dish, Integer> dishes;
}
