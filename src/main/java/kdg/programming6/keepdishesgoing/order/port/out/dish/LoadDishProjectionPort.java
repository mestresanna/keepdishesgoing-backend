package kdg.programming6.keepdishesgoing.order.port.out.dish;

import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadDishProjectionPort {
    Optional<DishProjection> loadbyIdByMenu(UUID dishId, UUID  menuId);
    List<DishProjection> loadAllWithDishId(UUID dishId);
    List<DishProjection> loadAllDishProjections(UUID menuId);

}

