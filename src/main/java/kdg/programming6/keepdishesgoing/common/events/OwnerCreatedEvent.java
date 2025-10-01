package kdg.programming6.keepdishesgoing.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record OwnerCreatedEvent(LocalDateTime eventPit, UUID owner)  implements DomainEvent {
    public OwnerCreatedEvent(UUID owner){
        this(LocalDateTime.now(), owner);
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
