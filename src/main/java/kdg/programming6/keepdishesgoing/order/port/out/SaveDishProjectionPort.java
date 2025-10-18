package kdg.programming6.keepdishesgoing.order.port.out;

import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;

import java.util.List;
import java.util.UUID;

public interface SaveDishProjectionPort {
    DishProjection saveDishProjection(DishProjection dishProjection, UUID menuid);
    List<DishProjection> saveDishProjections(List<DishProjection> dishProjections);
}
