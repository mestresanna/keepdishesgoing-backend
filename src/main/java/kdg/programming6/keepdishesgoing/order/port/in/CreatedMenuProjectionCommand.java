package kdg.programming6.keepdishesgoing.order.port.in;

import kdg.programming6.keepdishesgoing.order.domain.projections.DishProjection;

import java.util.List;
import java.util.UUID;

public record CreatedMenuProjectionCommand(UUID id, UUID restaurantId) {
}
