package kdg.programming6.keepdishesgoing.restaurant.domain.ov;

import java.util.UUID;

public record DishId (UUID dishId) {
    public static DishId of(UUID dishId) {
        return new DishId(dishId);
    }


}
