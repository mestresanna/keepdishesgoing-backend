package kdg.programming6.keepdishesgoing.order.port.in.menu;

import java.util.UUID;

public record UpdatedMenuProjectionCommand(UUID id, UUID restaurantId, Double avgPrice) {
}
