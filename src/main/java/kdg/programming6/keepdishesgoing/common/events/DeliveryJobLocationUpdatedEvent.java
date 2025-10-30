package kdg.programming6.keepdishesgoing.common.events;

import org.springframework.modulith.events.Externalized;
import java.time.LocalDateTime;
import java.util.UUID;

@Externalized("kdg.events::#{'delivery.' + #this.restaurantId() + '.order.location.v1'}")
public record DeliveryJobLocationUpdatedEvent(
        UUID eventId,
        LocalDateTime occurredAt,
        UUID restaurantId,
        UUID orderId,
        double lat,
        double lng
) implements DomainEvent {

    @Override
    public LocalDateTime eventPit() {
        return occurredAt;
    }
}
