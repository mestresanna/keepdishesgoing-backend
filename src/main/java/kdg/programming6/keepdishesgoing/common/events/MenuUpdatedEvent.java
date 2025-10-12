package kdg.programming6.keepdishesgoing.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record MenuUpdatedEvent(LocalDateTime eventPit, UUID restaurantId) implements DomainEvent {
    public MenuUpdatedEvent(UUID restaurantId){
        this(LocalDateTime.now(), restaurantId);
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
