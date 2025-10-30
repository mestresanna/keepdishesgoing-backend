package kdg.programming6.keepdishesgoing.common.events.order;

import kdg.programming6.keepdishesgoing.common.events.DomainEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderPickedUpEvent(
        UUID eventId,
        LocalDateTime occurredAt,
        UUID restaurantId,
        UUID orderId
) implements DomainEvent {

    public OrderPickedUpEvent(UUID restaurantId, UUID orderId) {
        this(UUID.randomUUID(), LocalDateTime.now(), restaurantId, orderId);
    }

    @Override
    public LocalDateTime eventPit() {
        return occurredAt;
    }
}
