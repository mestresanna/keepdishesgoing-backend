package kdg.programming6.keepdishesgoing.order.port.out;

import kdg.programming6.keepdishesgoing.order.domain.projections.MenuProjection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadMenuProjectionPort {
    Optional<MenuProjection> loadbyId(UUID menuId);
    Optional<List<MenuProjection>>  loadAllMenuProjections(UUID restaurantId);
}
