package kdg.programming6.keepdishesgoing.order.port.in.menu;

import java.util.UUID;

public record CreatedMenuProjectionCommand(UUID id, UUID restaurantId) {
}
