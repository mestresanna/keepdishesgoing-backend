package kdg.programming6.keepdishesgoing.order.domain.projections;

import java.util.List;
import java.util.UUID;

public class MenuProjection {
    private UUID id;
    private UUID restaurantId;
    private List<DishProjection> dishes;

    public MenuProjection(UUID id, UUID restaurantId, List<DishProjection> dishes) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.dishes = dishes;
    }

    public UUID getId() {
        return id;
    }
    public UUID getRestaurantId() {
        return restaurantId;
    }
    public List<DishProjection> getDishes() {
        return dishes;
    }
}
