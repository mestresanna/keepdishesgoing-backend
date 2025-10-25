package kdg.programming6.keepdishesgoing.order.domain.projections;

import java.util.UUID;

public record DishProjId(UUID menuId, UUID dishId) {
    public static DishProjId of(UUID menuId, UUID dishId) {
        return new DishProjId(menuId, dishId);
    }
}
