package kdg.programming6.keepdishesgoing.order.port.out;

import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;
import kdg.programming6.keepdishesgoing.order.domain.projections.MenuProjection;

import java.util.Optional;
import java.util.UUID;

public interface LoadDishProjectionPort {
    Optional<DishProjection> loadbyId(UUID dishId, UUID  menuId);

}
