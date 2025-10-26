package kdg.programming6.keepdishesgoing.common.events;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderStatusChangedEvent(LocalDateTime eventPit,
                                      UUID orderId,
                                      String newStatus,
                                      String reason,
                                      UUID restaurantId) implements DomainEvent {
    public OrderStatusChangedEvent(
            UUID orderId,
            String newStatus,
            String reason,
            UUID restaurantId) {
        this(
                LocalDateTime.now(),
                orderId,
                newStatus,
                reason,
                restaurantId
        );
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
