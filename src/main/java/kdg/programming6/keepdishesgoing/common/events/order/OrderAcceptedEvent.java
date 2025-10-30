package kdg.programming6.keepdishesgoing.common.events.order;

import kdg.programming6.keepdishesgoing.common.events.DomainEvent;
import kdg.programming6.keepdishesgoing.common.vo.Address;
import kdg.programming6.keepdishesgoing.common.vo.AddressDto;
import kdg.programming6.keepdishesgoing.common.vo.Coordinates;
import org.springframework.modulith.events.Externalized;

import java.time.LocalDateTime;
import java.util.UUID;
@Externalized("kdg.events::#{'restaurant.' + #this.restaurantId() + '.order.accepted.v1'}")
public record OrderAcceptedEvent(
        UUID eventId,
        LocalDateTime occurredAt,
        UUID restaurantId,
        UUID orderId,
        AddressDto pickupAddress,
        Coordinates pickupCoordinates,
        AddressDto dropoffAddress,
        Coordinates dropoffCoordinates
) implements DomainEvent {

    public OrderAcceptedEvent(UUID restaurantId,
                              UUID orderId,
                              Address pickupAddress,
                              Coordinates pickupCoordinates,
                              Address dropoffAddress,
                              Coordinates dropoffCoordinates) {
        this(
            UUID.randomUUID(),
            LocalDateTime.now(),
            restaurantId,
            orderId,
            AddressDto.from(pickupAddress),
            pickupCoordinates,
            AddressDto.from(dropoffAddress),
            dropoffCoordinates
        );
    }

    @Override
    public LocalDateTime eventPit() {
        return occurredAt;
    }

}
