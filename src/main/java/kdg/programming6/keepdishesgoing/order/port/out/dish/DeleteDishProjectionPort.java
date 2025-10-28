package kdg.programming6.keepdishesgoing.order.port.out.dish;

import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;

import java.util.UUID;

public interface DeleteDishProjectionPort {
    void deleteDishProjection(DishProjection dishProjection, UUID menuProjection);
}
