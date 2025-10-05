package kdg.programming6.keepdishesgoing.restaurant.domain;

import java.util.UUID;

public class Dish {
    private final UUID dishId;
    private final UUID restaurantId;
    private final String name;

    public Dish(UUID dishId, UUID restaurantId, String name) {
        this.dishId = dishId;
        this.restaurantId = restaurantId;
        this.name = name;
    }
}
